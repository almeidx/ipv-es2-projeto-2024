package com.es2.composite;

public abstract class EducationalComponent {
    private String name;

    public EducationalComponent(String name) {
        this.name = name;
    }

    public abstract void addComponent(EducationalComponent component);

    public String getName() {
        return name;
    }

    public abstract void display();
}
