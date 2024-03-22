package com.es2.factory;

public class EducationalObjectFactory {
    public static EducationalObject createObject(String type) throws UnknownEducationalObjectTypeException {
        return switch (type) {
            case "quiz" -> new Quiz();
            case "video" -> new Video();
            case "article" -> new Article();
            default -> throw new UnknownEducationalObjectTypeException("Unknown educational object type: " + type);
        };
    }
}
