import java.util.LinkedList;

// Наследование реализации (implementation inheritance)
// Используем для создания очереди программную реализацию связного списка
class MyQueue<T> extends LinkedList<T> {
    public void enqueue(T value) {
        this.addFirst(value);
    }

    public T dequeue() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return this.removeLast();
    }

    // остальные операции ...
}

// Льготное наследование (facility inheritance)
// Класс Thread уже содержит всю логику для создания потока
class DrawPointThread extends Thread {
    @Override
    public void run() {
        System.out.printf("%s starting draw new point%n", getName());
    }
}