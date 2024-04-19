import config.*;
import education.*;
import courses.*;

public class Main {
    public static void main(String[] args) throws UnknownConfigurationKeyException, UnknownEducationalEntityTypeException,
            LessonOperationException, ComponentNotFoundException, PoolExhaustedException, ObjectNotFoundException {
        Main.testSingleton();
        Main.testFactory();
        Main.testBridge();
        Main.testComposite();
        Main.testObjectPool();
    }

    private static void testSingleton() throws UnknownConfigurationKeyException {
        System.out.println("====== Singleton ======");

        ConfigurationManager configManager = ConfigurationManager.getInstance();
        configManager.setConfiguration("app.name", "E-Learning App");
        configManager.setConfiguration("app.version", "1.0.0");

        try {
            configManager.getConfiguration("app.author");
        } catch (UnknownConfigurationKeyException e) {
            System.out.println("Expected: " + e.getMessage());
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
            System.out.println("Expected: " + e.getMessage());
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

    private static void testComposite() throws LessonOperationException, ComponentNotFoundException {
        System.out.println("====== Composite ======");

        EducationalComponent lesson1 = new Lesson("Lesson 1");
        EducationalComponent lesson2 = new Lesson("Lesson 2");
        EducationalComponent course1 = new Course("Course 1");

        course1.addComponent(lesson1);
        course1.addComponent(lesson2);

        course1.display();

        course1.removeComponent(lesson2);

        try {
            course1.removeComponent(lesson2);
        } catch (ComponentNotFoundException e) {
            System.out.println("Expected: " + e.getMessage());
        }

        try {
            lesson1.addComponent(lesson2);
        } catch (LessonOperationException e) {
            System.out.println("Expected: " + e.getMessage());
        }

        course1.display();
    }

    private static void testObjectPool() throws PoolExhaustedException, ObjectNotFoundException {
        System.out.println("====== Object Pool ======");

        DatabaseConnection connection1 = DatabaseConnection.getConnection();
        DatabaseConnection connection2 = DatabaseConnection.getConnection();

        connection1.releaseConnection(connection1);
        connection2.releaseConnection(connection2);

        try {
            connection1.releaseConnection(connection1);
        } catch (ObjectNotFoundException e) {
            System.out.println("Expected: " + e.getMessage());
        }

        for (int i = 0; i < 10; i++) {
            DatabaseConnection.getConnection();
        }

        try {
            DatabaseConnection.getConnection();
        } catch (PoolExhaustedException e) {
            System.out.println("Expected: " + e.getMessage());
        }
    }
}
