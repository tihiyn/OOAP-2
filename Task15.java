// Неверный вариант
//class User {
//    private boolean isAdmin;
//
//    public boolean isAdmin() {
//        return isAdmin;
//    }
//}

class User {
    public static final int OK_CREATE_FILE = 0;
    public static final int PERMISSION_DENIED_CREATE_FILE = 1;

    private int createFileStatus;

    public void createFile(String name) {
        // может создать файл только в директории user
    }

    public int getCreateFileStatus() {
        return createFileStatus;
    }
}

class Manager extends User {
    @Override
    public void createFile(String name) {
        // может создать файл во сех директориях, кроме приватных
    }
}

class Admin extends User {
    @Override
    public void createFile(String name) {
        // создать файл в любой директории
    }
}

public class Task15 {
    public static void main(String[] args) {
//        Неверный вариант
//        User user = new User();
//        if (user.isAdmin()) {
//            ...
//        }

        User admin = new Admin();
        admin.createFile("private/test.txt");
    }
}
