class Task12 {
    public void success() {
        A a = new A();
        B b = new B();
        a = a.assignmentAttempt(a, b);
        if (General.ASSIGN_OK_STATUS == a.getAssignStatus()) {
            System.out.println(b.getTrueClass()); // выведется B
        }
    }

    public void fail() {
        A a = new A();
        B b = new B();
        b = b.assignmentAttempt(b, a);
        // проверка будет не пройдена, ничего не выведется
        if (General.ASSIGN_OK_STATUS == b.getAssignStatus()) {
            System.out.println(b.getTrueClass());
        }
    }
}

class A extends General {}

class B extends A {}

//abstract class General {
//    public static final int ASSIGN_OK_STATUS = 0;
//    public static final int ASSIGN_ERROR_STATUS = 1;
//    private int assignStatus = ASSIGN_OK_STATUS;
//
//    // попытка присваивания
//    public <T extends General> T assignmentAttempt(T target,  General source) {
//        if (target.getTrueClass().isInstance(source)) {
//            assignStatus = ASSIGN_OK_STATUS;
//            return (T) source;
//        }
//        assignStatus = ASSIGN_ERROR_STATUS;
//        return null;
//    }
//
//    public int getAssignStatus() {
//        return assignStatus;
//    }
//}
