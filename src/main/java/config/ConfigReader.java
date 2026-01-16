package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    
    static {
        properties = new Properties();
        try (FileInputStream file = new FileInputStream(
                System.getProperty("user.dir") + "/src/main/resources/config.properties")) {
            properties.load(file);
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
        }
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public static String getBaseUrl() {
        return getProperty("base.url");
    }
    
    public static String getUatUrl() {
        return getProperty("uat.url");
    }
    
    public static String getTestMobileNumber() {
        return getProperty("test.mobile.number");
    }
    
    public static String getTestOTP() {
        return getProperty("test.otp");
    }
    
    public static String getTestEmail() {
        return getProperty("test.email");
    }
    
    public static String getTestName() {
        return getProperty("test.name");
    }
    
    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("browser.headless"));
    }
    
    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait"));
    }
}