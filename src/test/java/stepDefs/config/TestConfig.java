package stepDefs.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
    private static final Properties properties = new Properties();

    // Read from the config.properties file and store in the properties field
    static {
        try (InputStream inputStream = TestConfig.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new IOException("Unable to find config.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBaseUri() {
        return properties.getProperty("api_url");
    }

    public static String getLoginPath() {
        return properties.getProperty("login_path");
    }

}
