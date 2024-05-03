package education;

public abstract class EducationalEntityDecorator implements EducationalEntity {
    protected final EducationalEntity entity;

    public EducationalEntityDecorator(EducationalEntity entity) {
        this.entity = entity;
    }

    public abstract void display();
}
