package com.mycomp.app.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * This class will hold the application configuration like
 * <ol>
 *    <li> Date Time Format</li>
 *    <li> Locale information</li>
 *    <li> I18n resource map</li>
 * </ol>
 */
public class AppConfig {
    /**
     * This fields will hold all the application configuration as key-value.
     */
    Map<String,String> configProp = new HashMap<>();

    /**
     * Load the information from default property file for the default configuration.
     */
    public void loadConfig(){
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("config.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        configProp.putAll(properties.entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().toString(),
                        e -> e.getValue().toString())));

    }

}
