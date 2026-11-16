//If S is a subtype of T, then objects of T may be replaced with objects of S without altering any of the desirable properties of that person
/*
Simple terms:
If class B is a subtype of class A, then you should be able to use B anywhere you used A, and the behavior should
remain constant.
 */
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
/*
Why does LSP Matter:
- Broken functionality when subclasses replaces parents
- Fragile inheritance hierarchies
- Bugs that are hard to detect
- Client code being tightly coupled to specific types

How to Spot LSP violations:
- Subclasses throwing unexpected exceptions for base class methods
- Sub class changes behavior so much that the code fails

Key principles:
- Design by contract
- Avoid over inheritance, use composition
- Refactor early
 */
