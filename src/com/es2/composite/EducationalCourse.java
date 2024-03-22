package com.es2.composite;

import java.util.ArrayList;
import java.util.List;

public class EducationalCourse extends EducationalComponent {
    private List<EducationalComponent> components;

    public EducationalCourse(String name) {
        super(name);
        components = new ArrayList<>();
    }

    @Override
    public void addComponent(EducationalComponent component) {
        components.add(component);
    }

    @Override
    public void display() {
        System.out.println("Course components:");
        for (EducationalComponent component : components) {
            System.out.println("- " + component.getName());
        }
    }
}
