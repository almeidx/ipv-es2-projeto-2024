package education;

public class EducationalEntityFactory {
    public static EducationalEntity makeObject(String type) throws UnknownEducationalEntityTypeException {
        return switch (type) {
            case "quiz" -> new Quiz();
            case "video" -> new Video();
            case "article" -> new Article();
            default -> throw new UnknownEducationalEntityTypeException("Unknown educational entity type: " + type);
        };
    }
}
