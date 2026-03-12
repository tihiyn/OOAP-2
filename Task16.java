class Human {}

class Man extends Human {}

class Woman extends Human {}

class Task16 {
    public static void main(String[] args) {
        Man[] mans = {new Man()};
        Human[] humans = mans; // полиморфное присваивание
        method(humans); // формальный параметр
    }

    public static void method(Human[] humans) {
        Woman woman = new Woman();
        // ковариантный вызов
        // !!! код скомпилируется, но упадёт в рантайме с ArrayStoreException
        // это возможно, так как массивы в Java, ковариантны
        // с дженериками такое не сработает, так как они инварианты
        humans[0] = woman;
    }
}