package com.mycomp.testhelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    private final Properties properties = new Properties();
    public void load(){
        try(InputStream inputStream = ClassLoader.getSystemResourceAsStream("test.properties")){
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Properties properties() {
        return properties;
    }
}
