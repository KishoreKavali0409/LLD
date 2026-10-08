class Analytics{
    private Analytics(){
        System.out.println("Analytics obj created");
    }
    private static class Holder{
        //Static inner class
        private static final Analytics INSTANCE = new Analytics();
    }
    //Global Access Point
    public static Analytics getInstance(){
        return Holder.INSTANCE;
    }
    public void trackEvent(String event){
        System.out.println("Tracking event" + event);
    }
}
public class SingleTonDesignPattern {
    static void main(String[] args) {
        Analytics obj1 = Analytics.getInstance();
        Analytics obj2 = Analytics.getInstance();
        obj1.trackEvent("User login");
        obj2.trackEvent("Order placed");
        System.out.println(obj1);
        System.out.println(obj2);
        if(obj1 == obj2){
            System.out.println("Both reference points to the same obj");
        }else{
            System.out.println("They both dont reference to the same obj");
        }
    }
}
