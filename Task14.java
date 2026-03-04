import java.util.ArrayList;
import java.util.List;

interface Addable<T> {
    T add(T val);
}

class Vector<T extends Any & Addable<T>> extends Any implements Addable<Vector<T>> {
    private final List<T> storage;

    public Vector () {
        this.storage = new ArrayList<>();
    }

    @Override
    public Vector<T> add(Vector<T> another) {
        if (this.getSize() != another.getSize()) {
            return null;
        }
        Vector<T> res = new Vector<>();
        for (int i = 0; i < this.getSize(); i++) {
            res.append(this.get(i).add(another.get(i)));
        }
        return res;
    }

    public void append(T val) {
        this.storage.add(val);
    }

    public T get(int idx) {
        return storage.get(idx);
    }

    public int getSize() {
        return storage.size();
    }
}

class MyInt extends Any implements Addable<MyInt> {
    private final int value;

    public MyInt(int value) {
        this.value = value;
    }

    @Override
    public MyInt add(MyInt val) {
        return new MyInt(this.value + val.value);
    }

    public String toString() {
        return Integer.toString(this.value);
    }
}

class Task14 {
    public static void main(String[] args) {
        Vector<MyInt> x1 = new Vector<>();
        x1.append(new MyInt(1));
        x1.append(new MyInt(2));
        Vector<MyInt> x2 = new Vector<>();
        x2.append(new MyInt(3));
        x2.append(new MyInt(4));
        Vector<Vector<MyInt>> xx = new Vector<>();
        xx.append(x1);
        xx.append(x2);
        Vector<Vector<Vector<MyInt>>> xxx = new Vector<>();
        xxx.append(xx);

        Vector<MyInt> y1 = new Vector<>();
        y1.append(new MyInt(5));
        y1.append(new MyInt(6));
        Vector<MyInt> y2 = new Vector<>();
        y2.append(new MyInt(7));
        y2.append(new MyInt(8));
        Vector<Vector<MyInt>> yy = new Vector<>();
        yy.append(y1);
        yy.append(y2);
        Vector<Vector<Vector<MyInt>>> yyy = new Vector<>();
        yyy.append(yy);

        Vector<Vector<Vector<MyInt>>> sum = xxx.add(yyy);
        for (int i = 0; i < sum.getSize(); i++) {
            for (int j = 0; j < sum.get(i).getSize(); j++) {
                for (int k = 0; k < sum.get(i).get(j).getSize(); k++) {
                    System.out.printf("%s ", sum.get(i).get(j).get(k)); // 6 8 10 12
                }
            }
        }
    }
}