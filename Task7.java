class Repository<T> {
    public T findAll() {
        // поиск
    }
}

class CachedRepository<T> extends Repository<T> {
    public T findAll() {
        // поиск и кэширование результата
    }
}

class Main {
    public static void main(String[] args) {
        Repository<Object> repo = new CachedRepository<>();
        repo.findAll(); // динамическое связывание - тип объекта repo и соответсвующая реализация метода findAll будут
        // определены автоматически компилятором во время выполнения программы
    }
}
