package com.qa.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyExtractor {
    public static String extractor(String key) throws IOException {
        Properties config = new Properties();
        FileInputStream configFile = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config/Config.properties");
        config.load(configFile);
        return config.getProperty(key);
    }
}