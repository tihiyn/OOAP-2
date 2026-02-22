abstract class Employee {
    private String firstName;
    private String lastName;

    protected final String getFullName() { // ключевое слово final для метода запрещает его переопределение
        return String.format("%s %s", lastName, firstName);
    }
}

class Engineer extends Employee {
    // не скомпилируется
    // public String getFullName() {...}
}
