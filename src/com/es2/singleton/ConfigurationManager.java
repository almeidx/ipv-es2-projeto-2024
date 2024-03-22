package com.es2.singleton;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private Map<String, String> configurations;

    private ConfigurationManager() {
        configurations = new HashMap<>();
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public void setConfiguration(String key, String value) {
        configurations.put(key, value);
    }

    public String getConfiguration(String key) throws UnknownConfigurationKeyException {
        if (!configurations.containsKey(key)) {
            throw new UnknownConfigurationKeyException("The key " + key + " is not present in the configuration");
        }

        return configurations.get(key);
    }
}
