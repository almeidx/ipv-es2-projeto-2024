package com.es2.composite;

public class EducationalLesson extends EducationalComponent {
    public EducationalLesson(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("Lesson: " + getName());
    }

    @Override
    public void addComponent(EducationalComponent component) {
        throw new UnsupportedOperationException("Lesson cannot have sub-components");
    }
}
