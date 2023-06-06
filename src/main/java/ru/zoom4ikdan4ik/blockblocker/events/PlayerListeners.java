package ru.zoom4ikdan4ik.blockblocker.events;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import ru.zoom4ikdan4ik.blockblocker.interfaces.IBlockBlocker;
import ru.zoom4ikdan4ik.blockblocker.managers.ConfigManager;

public class PlayerListeners implements IBlockBlocker {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onBlockPlace(PlayerInteractEvent event) {
        EntityPlayer entityPlayer = event.entityPlayer;

        if (entityPlayer != null) {
            World world = entityPlayer.worldObj;
            ItemStack itemStack = entityPlayer.getCurrentEquippedItem();

            if (itemStack != null)
                if (ConfigManager.blocksInChunk.contains(itemStack)) {
                    Block blockItemStack = Block.getBlockFromItem(itemStack.getItem());
                    this.configManager.debug("BlockItemStack: %%", blockItemStack.toString());

                    int x = event.x;
                    int z = event.z;
                    int blocks = 0;

                    Chunk chunk = world.getChunkFromBlockCoords(x, z);
                    ExtendedBlockStorage[] storages = chunk.getBlockStorageArray();

                    for (ExtendedBlockStorage storage : storages)
                        if (storage != null)
                            for (int xX = 0; xX < 16; ++xX)
                                for (int zZ = 0; zZ < 16; ++zZ)
                                    for (int yY = 0; yY < 16; ++yY) {
                                        Block blockStorage = storage.getBlockByExtId(xX, yY, zZ);

                                        this.configManager.debug("BlockStorage: %%", blockStorage.toString());

                                        if (blockStorage != null)
                                            if (blockItemStack == blockStorage)
                                                blocks++;
                                    }

                    this.configManager.debug("Blocks: %%", blocks);

                    if (blocks + 1 > ConfigManager.maxBlocks) {
                        entityPlayer.addChatMessage(new ChatComponentText(ConfigManager.message));

                        event.setCanceled(true);
                    }
                }
        }
    }
}
