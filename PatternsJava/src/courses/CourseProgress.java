package courses;

public class CourseProgress {
    private final String lessonName;
    private final Float progress;

    public CourseProgress(String lessonName, Float progress) {
        this.lessonName = lessonName;
        this.progress = progress;
    }

    public String getCurrentLesson() {
        return lessonName;
    }

    public Float getLessonProgress() {
        return progress;
    }
}
