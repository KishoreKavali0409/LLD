
interface PaymentGateway{
    void pay(String orderId, double amount);
}

class PayuGateway implements PaymentGateway{
    @Override
    public void pay(String orderId, double amount) {
        System.out.println("Paid rs" + amount + "using PayU for order:" + orderId);
    }
}

class RazorpayAPI{
    public void makePayment(String invoiceId, double amountInRupees){
        System.out.println("Paid: rs" + amountInRupees + "using razorpay for invoice:" + invoiceId);
    }
}

//Adapter
class RazorAdapter implements PaymentGateway{
    private RazorpayAPI razorpayAPI;
    public RazorAdapter(){
        this.razorpayAPI = new RazorpayAPI();
    }

    @Override
    public void pay(String orderId, double amount) {
        razorpayAPI.makePayment(orderId, amount);
    }
}

//Client
class CheckoutService{
    private PaymentGateway paymentGateway;
    public CheckoutService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }
    public void checkout(String orderId, double amount){
        paymentGateway.pay(orderId, amount);
    }
}

public class AdapterPattern {
    static void main(String[] args) {
        CheckoutService payUCheck = new CheckoutService(new PayuGateway());
        payUCheck.checkout("ORDER101", 500);
        System.out.println();
        //Using razorpay through adapter
        CheckoutService razorpayCheckout = new CheckoutService(new RazorAdapter());
        razorpayCheckout.checkout("ORDER102", 1000);
    }
}
