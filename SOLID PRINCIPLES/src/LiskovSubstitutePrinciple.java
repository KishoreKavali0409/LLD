class Notification{
    public void sendNotification(){
        System.out.println("Email sent");
    }
}

class TextNotification extends Notification {
    @Override
    public void sendNotification() {
        System.out.println("Text sent");
    }
}

class WhatsappTextNotification extends Notification {
    @Override
    public void sendNotification() {
        System.out.println("Whats app notification sent");
    }
}

public class LiskovSubstitutePrinciple {
    public static void main() {
        Notification notification = new WhatsappTextNotification();
        notification.sendNotification();
    }

}
