import config.*;
import education.*;

public class Main {
    public static void main(String[] args) throws UnknownConfigurationKeyException, UnknownEducationalEntityTypeException {
        Main.testSingleton();
        Main.testFactory();
        Main.testBridge();
    }

    private static void testSingleton() throws UnknownConfigurationKeyException {
        System.out.println("====== Singleton ======");

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

    private static void testFactory() throws UnknownEducationalEntityTypeException {
        System.out.println("====== Factory ======");

        EducationalEntity quiz = EducationalEntityFactory.makeObject("quiz");
        EducationalEntity video = EducationalEntityFactory.makeObject("video");
        EducationalEntity article = EducationalEntityFactory.makeObject("article");

        try {
            EducationalEntityFactory.makeObject("não existe");
        } catch (UnknownEducationalEntityTypeException e) {
            System.out.println(e.getMessage());
        }

        quiz.display();
        video.display();
        article.display();
    }

    private static void testBridge() {
        System.out.println("====== Bridge ======");

        Platform webPlatform = new WebPlatform();

        EducationalContent quizWeb = new QuizContent(webPlatform);
        EducationalContent quizMobile = new QuizContent(new MobilePlatform());

        quizWeb.display();
        quizMobile.display();
    }
}
