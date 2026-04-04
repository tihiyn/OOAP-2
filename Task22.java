// иерархия "Тип платежа"
abstract class Payment {
    private final Method m;
    private int processStatus;

    public static final int PROCESS_NOTHING = -1;
    public static final int PROCESS_OK = 0;
    public static final int PROCESS_ERROR = 11;

    public Payment(Method m) {
        this.m = m;
        processStatus = PROCESS_NOTHING;
    }

    abstract public void process();

    public int getProcessStatus() {
        return processStatus;
    }
}

class Order extends Payment {
    public Order(Method m) {
        super(m);
    }

    @Override
    public void process() {}
}

class Subscription extends Payment {
    public Subscription(Method m) {
        super(m);
    }

    @Override
    public void process() {}
}

// Иерархия "Способ оплаты"
abstract class Method {}

class Card extends Method {}

class Crypto extends Method {}

// Платёж может характеризоваться как типом (оплата заказа, подписки, услуги и др.), так и способом оплаты
// (банковская карта, крипта, наличные и др.). Зачастую, важнее тип платежа, чем способ оплаты, поэтому
// способ оплаты является полиморфным полем типа платежа.