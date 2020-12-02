package maow.test;

public class TestClass {
    private final String field = "Meme School";
    private final String testField;

    private TestClass(String testField) {
        this.testField = testField;
    }

    private void print() {
        System.out.println("Instance grabbed successfully.");
    }

    @Override
    public String toString() {
        return "Class created successfully - Test field: " + testField;
    }
}
