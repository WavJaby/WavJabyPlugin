package com.wavjaby;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

import static com.wavjaby.Color.printError;

public class ConfigManager {
    private main plugin = main.getPlugin(main.class);

    //Files and Configs here
    static FileConfiguration config;
    public File configFile;

    //Files and Configs here
    private final String[] langList = {"EN_US", "ZH_TW"};
    static FileConfiguration language;
    public File languageFile;

    void setup() {
        if (!plugin.getDataFolder().exists())//如果資料夾不存在
            plugin.getDataFolder().mkdir();//創建資料夾

        File folder = new File(plugin.getDataFolder(), "/language/");
        if (!folder.exists())//如果資料夾不存在
            folder.mkdir();//創建資料夾

        for (int i = 0; i < langList.length; i++) {
            //語言檔案
            File langFile = new File(plugin.getDataFolder(), "/language/" + langList[i] + ".yml");
            //語言檔案不存在
            if (!langFile.exists()) {
                language = createFile(langFile, "/language/" + langList[i] + ".yml");
            }
        }

        //設定檔檔案
        configFile = new File(plugin.getDataFolder(), "/config.yml");

        //設定檔檔案不存在
        if (!configFile.exists()) {
            //創建設定檔檔案
            config = createFile(configFile, "/config.yml");
        }

        //設定檔 = 設定檔檔案
        config = YamlConfiguration.loadConfiguration(configFile);

        //取得設定的語言
        languageFile = new File(plugin.getDataFolder(), "/language/" + config.getString("settings.language") + ".yml");
    }

    public void saveConfig() {
        try {
            config.save(configFile);//儲存config to file
            //檔案已處存
            main.console.sendMessage(main.pluginPrefix + language.getString("fileSaved")
                    .replace("&", "§"));
        } catch (IOException e) {
            //檔案無法處存
            printError(main.errorPluginPrefix + language.getString("saveFileFailed"));
        }
    }

    public void reloadConfig() {
        //reload configuration form file
        config = YamlConfiguration.loadConfiguration(configFile);

        //取得設定的語言
        languageFile = new File(plugin.getDataFolder(), "/language/" + config.getString("settings.language") + ".yml");

        language = YamlConfiguration.loadConfiguration(languageFile);
        //檔案已重新讀取
        main.console.sendMessage(main.pluginPrefix + language.getString("fileReloaded")
                .replace("&", "§"));
    }

    private YamlConfiguration createFile(File ymlFile, String filePath) {
        try {
            ymlFile.createNewFile();

            InputStream file = main.class.getResourceAsStream(filePath);//jar裡的default config
            BufferedReader reader = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));//read UTF-8

            FileWriter fileWriter = new FileWriter(ymlFile);//寫入器

            String line;
            while ((line = reader.readLine()) != null) //讀取直到沒有資料
                fileWriter.write(line + "\n");//寫到檔案裡

            fileWriter.close();//讀取器關閉

            if (language == null)//如果沒有語言檔
                //檔案已創建訊息
                main.console.sendMessage(main.pluginPrefix + "&aThe %fileName% file has been created."
                        .replace("&", "§")
                        .replace("%fileName%", ymlFile.getName()));
            else
                main.console.sendMessage(main.pluginPrefix + language.getString("fileCreated")
                        .replace("&", "§")
                        .replace("%fileName%", ymlFile.getName()));

            //load configuration form file
            return YamlConfiguration.loadConfiguration(ymlFile);
        } catch (IOException e) {
            if (language == null)//如果沒有語言檔
                //檔案無法創建訊息
                printError(main.pluginPrefix + "Could not crate the %fileName% file."
                        .replace("&", "§")
                        .replace("%fileName%", ymlFile.getName()));
            else
                //檔案無法創建訊息
                printError(main.pluginPrefix + language.getString("createFileFailed")
                        .replace("&", "§")
                        .replace("%fileName%", ymlFile.getName()));

            printError(e.getMessage());
            return null;
        }
    }
}
