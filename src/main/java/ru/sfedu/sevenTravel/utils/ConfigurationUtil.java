package ru.sfedu.sevenTravel.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Configuration utility. Allows to get configuration properties from the
 * default configuration file
 *
 * @author bjmailov
 */
public class ConfigurationUtil {

    private static String ConfigPath="/conf.properties";

    private static Properties configuration = new Properties();

    /**
     * Hides default constructor
     */
    private ConfigurationUtil(){}

    private static Properties getConfiguration() throws IOException {
        if (configuration.isEmpty()) {
            loadConfiguration();
        }
        return configuration;
    }

    /**
     * Loads configuration from <code>defaultConfigPath</code>
     */
    private static void loadConfiguration() {
        try (InputStream in = ConfigurationUtil.class.getResourceAsStream(ConfigPath)) {
            configuration.load(new InputStreamReader(in, "UTF-8"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Gets configuration entry value
     *
     * @param key Entry key
     * @return Entry value by key
     */
    public static String readConfig(String key) {
        try {
            return getConfiguration().getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
