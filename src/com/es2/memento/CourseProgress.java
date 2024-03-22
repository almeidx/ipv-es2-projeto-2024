package com.es2.memento;

public class CourseProgress {
    private String currentLesson;
    private int lessonProgress;

    public CourseProgress(String currentLesson, int lessonProgress) {
        this.currentLesson = currentLesson;
        this.lessonProgress = lessonProgress;
    }

    public String getCurrentLesson() {
        return currentLesson;
    }

    public int getLessonProgress() {
        return lessonProgress;
    }
}