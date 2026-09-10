public class PaymentExample {
    public static void main(String[] args) {
        Payment p = new PayPal();
        p.process(); // runs PayPal's version automatically
    }
}

interface Payment {
    void process();
}

class CreditCard implements Payment {
    public void process() {
        System.out.println("Charging card...");
    }
}

class PayPal implements Payment {
    public void process() {
        System.out.println("Charging PayPal...");
    }
}
