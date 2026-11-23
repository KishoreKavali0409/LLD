/*
Adapter is a structural design pattern that allows incompatible interfaces to work together by converting
the interface of one class into an interface expected by the client.

Simpler Words:
Instead of modifying the existing API, we create an Adapter.
Client -> Payment Gateway -> Razorpay Adapter -> Razorpay API
 */

// Target Interface
interface PaymentGateway{
    void pay(String orderId, double amount);
}

//Existing/Compatible implementation
class PayuGateway implements PaymentGateway{
    @Override
    public void pay(String orderId, double amount) {
        System.out.println("Paid rs" + amount + "using PayU for order:" + orderId);
    }
}

//Existing third party API
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

//Main
public class AdapterPattern {
    static void main(String[] args) {
        //Using payU directly
        CheckoutService payUCheck = new CheckoutService(new PayuGateway());
        payUCheck.checkout("ORDER101", 500);
        System.out.println();
        //Using razorpay through adapter
        CheckoutService razorpayCheckout = new CheckoutService(new RazorAdapter());
        razorpayCheckout.checkout("ORDER102", 1000);
    }
}

/*
Pros:
1. Allows incompatible classes to work together.
2. Follows open/closed principle.
3. Useful for third party integrations.
4. Reduces coupling.
5. Easy to support multiple implementations.

Cons:
1. Adds am extra layer
2. Too many adapters can increase complexity.
3. Adapter may need complex transformations.
4. Can hide differences b/w APIs

When to use:
1. Existing class has the functionality you need but incompatible interface.
2. Integrating third party libraries.
3. Legacy code integrations
4. You cannot modify the existing class.

 */
