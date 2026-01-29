class Bird {
    private final String voice;

    public Bird(String voice) {
        this.voice = voice;
    }

    public void fly() {
        System.out.println("I can fly");
    }
}

// пример специализации класса-родителя: утка - более специфичный случай птицы
class Duck extends Bird {
    // задаём специфичный голос
    public Duck() {
        super("Quack");
    }

    // добавляем специфичную способность
    public void swim() {
        System.out.println("I can swim");
    }
}

//----------------------------------------------------------------------------

class ElectricGuitar {
    private int volume;

    public void play() {
        System.out.println("GGGGGG");
    }

    public void makeLouder(int db) {
        volume+=db;
    }

    public void makeQuieter(int db) {
        volume-= db;
    }
}

// пример расширения класса-родителя: гитара - более общий случай электрогитары
class Guitar extends ElectricGuitar {
    // операции громче/тише специфичны для электрогитары, в общем случае они не поддерживаются
    @Override
    public void makeLouder(int db) {
        // not supported
    }

    @Override
    public void makeQuieter(int db) {
        // not supported
    }
}
