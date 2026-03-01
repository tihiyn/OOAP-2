class X {
    public void a() {}

    private void b() {}
}

class Y extends X {}

public class Task13 {
    public static void main(String[] args) {
        X x = new X();
        Y y = new Y();

        // 1. метод публичен в родительском классе X и публичен в его потомке Y
        x.a();
        y.a();

        // 4. метод скрыт в родительском классе X и скрыт в его потомке Y.
        // x.b(); ошибка компиляции
        // y.b(); ошибка компиляции
    }
}
