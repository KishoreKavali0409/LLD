interface PaymentGateway{
    void processPayment(double amount);
}

class RazorpayGateway implements PaymentGateway{
    @Override
    public void processPayment(double amount){
        System.out.println("Processing INR payment via razorpay: Rs"+ amount);
    }
}

class PayUGateway implements PaymentGateway{
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via PayU: Rs" + amount);
    }
}

class StripeGateway implements PaymentGateway{
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing USD payment via Stripe: $" + amount);
    }
}

class PayPal implements PaymentGateway{
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing USD payment via PayPal: $" + amount);
    }
}

interface Invoice{
    void generateInvoice();
}

class GSTInvoice implements Invoice{
    @Override
    public void generateInvoice() {
        System.out.println("Generating GST invoice for India ");
    }
}

class USInvoice implements Invoice{
    @Override
    public void generateInvoice() {
        System.out.println("Generating US complaint invoice ");
    }
}

interface RegionFactory{
    PaymentGateway createPaymentGateway(String gatewayType);
    Invoice createInvoice();
}

class IndiaFactory implements RegionFactory{
    @Override
    public PaymentGateway createPaymentGateway(String gatewayType) {
        switch(gatewayType.toLowerCase()){
            case "razorpay":
                return new RazorpayGateway();
            case "payu":
                return new PayUGateway();
            default:
                throw new IllegalArgumentException("Unsupported payment gateway in India:" + gatewayType);
        }
    }

    @Override
    public Invoice createInvoice() {
        return new GSTInvoice();
    }
}

class USFactory implements RegionFactory{
    @Override
    public PaymentGateway createPaymentGateway(String gatewayType) {
        switch(gatewayType.toLowerCase()){
            case "stripe":
                return new StripeGateway();
            case "paypal":
                return new PayPal();
            default:
                throw new IllegalArgumentException("Unsupported payment gateway in USA" + gatewayType);
        }
    }
    @Override
    public Invoice createInvoice() {
        return new USInvoice();
    }
}

//CLIENT
    class CheckoutService{
        private PaymentGateway paymentGateway;
        private Invoice invoice;
        public CheckoutService(RegionFactory factory, String gatewayType){
            this.paymentGateway = factory.createPaymentGateway(gatewayType);
            this.invoice = factory.createInvoice();
        }
        public void completeOrder(double amount){
            paymentGateway.processPayment(amount);
            invoice.generateInvoice();
        }
    }
public class AbstractFactory {
    public static void main(String[] args) {
        CheckoutService indiaCheckout =
                new CheckoutService(new IndiaFactory(), "razorpay");
        indiaCheckout.completeOrder(5000);
        System.out.println();
        CheckoutService usCheckout =
                new CheckoutService(new USFactory(), "stripe");
        usCheckout.completeOrder(100);
    }
}