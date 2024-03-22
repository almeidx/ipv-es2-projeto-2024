import config.ConfigurationManager;
import config.UnknownConfigurationKeyException;

public class Main {
    public static void main(String[] args) throws UnknownConfigurationKeyException {
        // Test Singleton pattern
        ConfigurationManager configManager = ConfigurationManager.getInstance();
        configManager.setConfiguration("app.name", "E-Learning App");
        configManager.setConfiguration("app.version", "1.0.0");

        try {
            configManager.getConfiguration("app.author");
        } catch (UnknownConfigurationKeyException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("App Name: " + configManager.getConfiguration("app.name"));
        System.out.println("App Version: " + configManager.getConfiguration("app.version"));
    }
}
