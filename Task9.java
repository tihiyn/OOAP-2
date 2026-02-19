import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class General {
    // глубокое копирование
    public void copy(General from) {
        if (from == null) {
            throw new NullPointerException("Источник копирования не может быть null");
        }
        if (!this.getTrueClass().equals(from.getTrueClass())) {
            throw new IllegalArgumentException("Тип источника копирования не совпадает с текущим типом");
        }
        General cloned = from.clone();
        getAllFields(cloned.getTrueClass()).forEach(f -> {
            f.setAccessible(true);
            try {
                f.set(this, f.get(cloned));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Ошибка при чтении из поля %s".formatted(f.getName()));
            }
        });
    }

    private List<Field> getAllFields(Class<?> clazz) {
        return getAllFieldsRecursive(clazz, new ArrayList<>());
    }

    private List<Field> getAllFieldsRecursive(Class<?> clazz, List<Field> fields) {
        if (clazz == null || clazz.equals(General.class)) {
            return new ArrayList<>();
        }
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        return getAllFieldsRecursive(clazz.getSuperclass(), fields);
    }

    // глубокое клонирование
    public General clone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
            return (General) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сериализации/десериализации", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Ошибка при десериализации в объект", e);
        }
    }

    // глубокое сравнение
    public boolean equals(General o) {
        if (this == o) {
            return true;
        }
        if (o == null || !this.getTrueClass().equals(o.getTrueClass())) {
            return false;
        }
        for (Field f: this.getAllFields(this.getTrueClass())) {
            f.setAccessible(true);
            try {
                General thisVal = (General) f.get(this);
                General otherVal = (General) f.get(o);
                if (thisVal == null && otherVal == null) {
                    continue;
                }
                if (thisVal == null || otherVal == null) {
                    return false;
                }
                if (!thisVal.equals(otherVal)) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Ошибка при чтении из поля %s".formatted(f.getName()));
            }
        }
        return true;
    }

    // сериализация
    public String serialize() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (XMLEncoder encoder = new XMLEncoder(baos)) {
            encoder.writeObject(this);
        }
        return baos.toString();
    }

    // десериализация
    public void deserialize(String xml) {
        try (XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(xml.getBytes()))) {
            copy((General) decoder.readObject());
        }
    }

    // печать
    public void print() {
        StringBuilder sb = new StringBuilder(this.getTrueClass().getSimpleName())
            .append(" {");
        Field[] fields = getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                sb.append(fields[i].getName())
                    .append("=")
                    .append(fields[i].get(this));
                if (i < fields.length - 1) {
                    sb.append(", ");
                }
            } catch (IllegalAccessException e) {
                sb.append(fields[i].getName())
                    .append("=error");
            }
        }
        sb.append("}");
        System.out.println(sb);
    }

    // проверка типа
    public boolean instanceOf(Class<?> clazz) {
        return clazz.isInstance(this);
    }

    // получение реального типа объекта
    public Class<?> getTrueClass() {
        return this.getClass();
    }
}

abstract class Any extends General {}