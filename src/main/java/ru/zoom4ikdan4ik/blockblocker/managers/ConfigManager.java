package ru.zoom4ikdan4ik.blockblocker.managers;

import com.gamerforea.eventhelper.config.*;

@Config(name = ConfigManager.MODID)
public class ConfigManager {
    public static final String MODID = "BlockBlocker";
    public static final String MODNAME = "BlockBlocker";

    @ConfigItemBlockList(comment = "Проверка блоков на опред. кол-во в чанке")
    public static ItemBlockList blocksInChunk = new ItemBlockList(true);

    @ConfigInt(comment = "Количество блоков в чанке", min = 0)
    public static int maxBlocks = 10;

    @ConfigString(comment = "Сообщение игроку, если больше  лимита")
    public static String message = "§4Данный блок нельзя сюда поставить";

    @ConfigBoolean(comment = "Откладка")
    public static boolean debugMod = false;

    public ConfigManager() {
        ConfigUtils.readConfig(ConfigManager.class);
    }

    public void debug(String message, Object... objects) {
        for (Object object : objects)
            message = message.replace("%%", String.valueOf(object));

        if (debugMod)
            System.out.println(message);
    }

}
