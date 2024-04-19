package courses;

public class Lesson implements EducationalComponent {
    private String name;

    public Lesson(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println("Displaying lesson: " + name);
    }

    public void addComponent(EducationalComponent component) throws LessonOperationException {
        throw new LessonOperationException("Cannot add to a lesson");
    }

    public void removeComponent(EducationalComponent component) throws LessonOperationException {
        throw new LessonOperationException("Cannot remove from a lesson");
    }
}
