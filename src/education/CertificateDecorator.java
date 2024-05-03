package education;

public class CertificateDecorator extends EducationalEntityDecorator {
    public CertificateDecorator(EducationalEntity entity) {
        super(entity);
    }

    public void display() {
        entity.display();
        System.out.println("Adding certificate");
    }
}
