package courses;

import java.util.ArrayList;

public class Course implements EducationalComponent {
    private String name;
    private ArrayList<EducationalComponent> components;

    public Course(String name) {
        this.name = name;
        components = new ArrayList<>();
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
}
