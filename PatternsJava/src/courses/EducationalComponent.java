package courses;

public interface EducationalComponent {
    void display();
    void addComponent(EducationalComponent component) throws LessonOperationException;
    void removeComponent(EducationalComponent component) throws LessonOperationException, ComponentNotFoundException;
}
