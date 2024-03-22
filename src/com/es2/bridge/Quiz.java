package com.es2.bridge;

public class Quiz implements EducationalContent {
    private final Platform platform;

    public Quiz(Platform platform) {
        this.platform = platform;
    }

    @Override
    public void display() {
        platform.displayContent();
    }
}