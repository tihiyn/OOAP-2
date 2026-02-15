import java.util.List;
import java.util.function.Consumer;

public class Task8 {
    private void co(List<? extends Bishop> list) {
        list.stream().forEach(b -> b.moveOnDiagonal());
        // пример ковариантности - схема наследования остаётся прежней
        // объектов типа Queen меньше, чем объектов типа Bishop
        // объектов типа List<Queen> меньше, чем объектов типа List<Bishop>
    }

    private void contr(Consumer<? super Queen> consumer) {
        consumer.accept(new Queen());
        // пример контрвариантности - делегатов для Queen больше, чем делегатов для Bishop,
        // хотя объектов типа Queen меньше, чем объектов типа Bishop
        // consumer.accept(new Bishop()) - не сработает, так как, например, moveOnHorizontal() нельзя применить к Bishop
    }
}

class Bishop {
    public void moveOnDiagonal() {

    }
}

class Queen extends Bishop {
    public void moveOnVertical() {

    }

    public void moveOnHorizontal() {
    }
}