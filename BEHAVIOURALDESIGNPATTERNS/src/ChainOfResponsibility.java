abstract class SupportHandler{
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    public abstract void handleRequest(String requestType);
}
class GeneralSupport extends SupportHandler{
    public void handleRequest(String requestType){
        if(requestType.equalsIgnoreCase("general")){
            System.out.println("general support : Handling general query");
        }else if(nextHandler != null){
            nextHandler.handleRequest(requestType);
        }
    }
}
class BillingSupport extends SupportHandler{
    public void handleRequest(String requestType) {
        if(requestType.equalsIgnoreCase("refund")){
            System.out.println("Billing Support : Handling refund request");
        }else if(nextHandler != null){
            nextHandler.handleRequest(requestType);
        }
    }
}
class TechnicalSupport extends SupportHandler{
    public void handleRequest(String requestType){
        if(requestType.equalsIgnoreCase("technical")){
            System.out.println("Technical Support : Handling technical issue");
        }else if(nextHandler != null){
            nextHandler.handleRequest(requestType);
        }
    }
}
class DeliverySupport extends SupportHandler{
    public void handleRequest(String requestType) {
        if(requestType.equalsIgnoreCase("delivery")){
            System.out.println("Delivery support : handling delivery issue");
        }else if(nextHandler != null){
            System.out.println("Delivery support : No handler found for request");
        }
    }
}
public class ChainOfResponsibility {
    public static void main(String[] args) {
        SupportHandler general = new GeneralSupport();
        SupportHandler billing = new BillingSupport();
        SupportHandler technical = new TechnicalSupport();
        SupportHandler delivery = new DeliverySupport();
        general.setNextHandler(billing);
        billing.setNextHandler(technical);
        technical.setNextHandler(delivery);
        general.handleRequest("delivery");
    }
}
