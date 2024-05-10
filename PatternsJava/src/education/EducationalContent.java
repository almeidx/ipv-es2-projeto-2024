package education;

public abstract class EducationalContent {
    private final Platform platform;

    public EducationalContent(Platform platform) {
        this.platform = platform;
    }

    public void display() {
        platform.display();
    }
}
