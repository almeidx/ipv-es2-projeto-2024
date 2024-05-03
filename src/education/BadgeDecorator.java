package education;

public class BadgeDecorator extends EducationalEntityDecorator {
    public BadgeDecorator(EducationalEntity entity) {
        super(entity);
    }

    public void display() {
        entity.display();
        System.out.println("Adding badge");
    }
}
