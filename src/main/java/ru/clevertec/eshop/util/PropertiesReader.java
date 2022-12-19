package ru.clevertec.eshop.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static Properties properties = new Properties();

    public static Properties getProperties(String propertiesFile) {
        InputStream inputStream = PropertiesReader.class.getClassLoader()
                .getResourceAsStream(propertiesFile);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            //LOGGER.error("Can not get information from properties file", e);
        }
        return properties;
    }
}
