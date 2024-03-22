import com.es2.memento.Course;
import com.es2.singleton.*;

import com.es2.factory.EducationalObject;
import com.es2.factory.EducationalObjectFactory;
import com.es2.factory.UnknownEducationalObjectTypeException;

import com.es2.bridge.*;
import com.es2.composite.*;
import com.es2.objectpool.*;
import com.es2.memento.*;

public class Main {
    public static void main(String[] args) throws UnknownConfigurationKeyException, UnknownEducationalObjectTypeException {
        // Test Singleton pattern
        ConfigurationManager configManager = ConfigurationManager.getInstance();
        configManager.setConfiguration("app.name", "E-Learning App");
        configManager.setConfiguration("app.version", "1.0");
        System.out.println("App Name: " + configManager.getConfiguration("app.name"));
        System.out.println("App Version: " + configManager.getConfiguration("app.version"));

        // Test Factory pattern
        EducationalObject quiz = EducationalObjectFactory.createObject("quiz");
        EducationalObject video = EducationalObjectFactory.createObject("video");
        EducationalObject article = EducationalObjectFactory.createObject("article");
        quiz.display();
        video.display();
        article.display();

        // Test Bridge pattern
        EducationalContent quizWeb = new Quiz(new WebPlatform());
        EducationalContent quizMobile = new Quiz(new MobilePlatform());
        quizWeb.display();
        quizMobile.display();

        // Test Composite pattern
        EducationalComponent lesson1 = new EducationalLesson("Lesson 1");
        EducationalComponent lesson2 = new EducationalLesson("Lesson 2");
        EducationalComponent course1 = new EducationalCourse("Course 1");
        course1.addComponent(lesson1);
        course1.addComponent(lesson2);
        course1.display();

        // Test Object Pool pattern
        DatabaseConnection connection1 = DatabaseConnection.getConnection();
        DatabaseConnection connection2 = DatabaseConnection.getConnection();
        // Use connections for some operations
        connection1.releaseConnection(connection1);
        connection2.releaseConnection(connection2);

        // Test Memento pattern
        Course myCourse = new Course("My Course");
        CourseProgress progress1 = new CourseProgress("Lesson 1", 50);
        myCourse.setProgress(progress1);
        CourseProgress savedProgress = myCourse.saveProgress();
        CourseProgress progress2 = new CourseProgress("Lesson 2", 25);
        myCourse.setProgress(progress2);
        myCourse.restoreProgress(savedProgress);
        System.out.println("Current Lesson: " + myCourse.progress.getCurrentLesson());
        System.out.println("Lesson Progress: " + myCourse.progress.getLessonProgress());

        // Test Decorator pattern
        //EducationalObject quizWithBadge = new BadgeDecorator(new Quiz());
        //quizWithBadge.display();
        //EducationalObject quizWithCertificate = new CertificateDecorator(quizWithBadge);
        //quizWithCertificate.display();
    }
}

/*
 1. Singleton: A instância singleton de `ConfigurationManager` é obtida e utilizada para definir e obter configurações globais.
 2. Factory: O método `createObject` da classe `EducationalObjectFactory` é utilizado para criar diferentes tipos de objetos educacionais (quiz, vídeo e artigo), que são exibidos.
 3. Bridge: Diferentes implementações de `PresentationPlatform` (web e mobile) são utilizadas com a abstração `Quiz` para exibir o conteúdo educacional em diferentes plataformas.
 4. Composite: Uma estrutura composta de lições e cursos é criada, onde um curso contém duas lições, e a exibição é tratada de forma uniforme.
 5. Object Pool: Duas conexões à base de dados são obtidas a partir do pool, utilizadas e, em seguida, liberadas de volta para o pool.
 6. Memento: O progresso de um curso é salvo, alterado e, em seguida, restaurado para o estado anterior.
 7. Decorator: Um objeto `Quiz` é decorado com badges e certificados, e a exibição é realizada com as funcionalidades adicionais.
 */
