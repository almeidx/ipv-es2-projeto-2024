package com.es2.memento;

public class Course {
    private String name;
    public CourseProgress progress;

    public Course(String name) {
        this.name = name;
    }

    public void setProgress(CourseProgress progress) {
        this.progress = progress;
    }

    public CourseProgress saveProgress() {
        return new CourseProgress(progress.getCurrentLesson(), progress.getLessonProgress());
    }

    public void restoreProgress(CourseProgress savedProgress) {
        this.progress = savedProgress;
    }

    public void displayProgress() {
        System.out.println("Current Lesson: " + progress.getCurrentLesson());
        System.out.println("Lesson Progress: " + progress.getLessonProgress() + "%");
    }
}