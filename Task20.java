import java.math.BigDecimal;

// Наследования вариаций
class Animal {
    protected String name;

    public String getVoice() {
        return "I am a %s".formatted(name);
    }
}

class Cat extends Animal {
    @Override
    public String getVoice() {
        return "I am a %s, mew-mew".formatted(name);
    }
}

// Наследование с конкретизацией (reification inheritance)
abstract class Shape {
    public abstract double getArea();
}

class Circle extends Shape {
    private final double r;

    public Circle(double r) {
        this.r = r;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(r, 2);
    }
}

// Структурное наследование (structure inheritance)
class CPU implements Comparable<BigDecimal> {
    private BigDecimal frequency;

    @Override
    public int compareTo(BigDecimal o) {
        return this.frequency.compareTo(o);
    }
}