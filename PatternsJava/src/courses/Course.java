package courses;

import java.util.ArrayList;

public class Course implements EducationalComponent {
    private final String name;
    private final ArrayList<EducationalComponent> components;
    public CourseProgress progress;
    private final ArrayList<CourseProgress> backups;

    public Course(String name) {
        this.name = name;
        components = new ArrayList<>();
        backups = new ArrayList<>();
    }

    public void display() {
        System.out.println("Displaying course: " + name);
        for (EducationalComponent component : components) {
            component.display();
        }
    }

    public void addComponent(EducationalComponent component) {
        components.add(component);
    }

    public void removeComponent(EducationalComponent component) throws ComponentNotFoundException {
        if (!components.contains(component)) {
            throw new ComponentNotFoundException("Component not found");
        }

        components.remove(component);
    }

    public void setProgress(CourseProgress progress) {
        if (this.progress != null) {
            backups.add(this.progress);
        }

        this.progress = progress;
    }
    
    public CourseProgress saveProgress() throws NoProgressSetException {
        if (progress == null) {
            throw new NoProgressSetException("No progress to save");
        }

        if (!backups.contains(progress)) {
            backups.add(progress);
        }

        return progress;
    }

    public void restoreProgress(CourseProgress progress) throws CourseProgressNotFoundException {
        if (!backups.contains(progress)) {
            throw new CourseProgressNotFoundException("Progress not found");
        }

        this.progress = progress;

        backups.remove(progress);
    }
}
