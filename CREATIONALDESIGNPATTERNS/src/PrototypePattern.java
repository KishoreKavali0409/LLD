/*
Prototype is a creational design pattern used when creating an object from scratch is costly or time - consuming,
so instead of creating a new object, we clone an existing object.

Expensive object -> prototype -> clone() ->New obj
instead of repeatedly doing new WelcomeEmail()

 */
import java.util.HashMap;
import java.util.Map;

interface EmailTemplate extends Cloneable{
    EmailTemplate clone();
    void setContent(String content);
    void send(String to);
}

//Concrete Prototype
class WelcomeEmail implements EmailTemplate{
    private String subject;
    private String content;
    public WelcomeEmail(){
        this.subject = "Welcome to TUF+";
        this.content = "HI there! thanks for joining us";
    }

    @Override
    public EmailTemplate clone() {
        try{
            return (WelcomeEmail) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("clone failed", e);
        }
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void send(String to) {
        System.out.println("Sending to " + to + ";[" + subject + "]" + content);
    }
}

//Prototype registry
class EmailTemplateRegistry{
    private static final Map<String, EmailTemplate> templates = new HashMap<>();
    static{
        templates.put(
                "Welcome",
                new WelcomeEmail()
        );
        //Add more templates here
    }
    public static EmailTemplate getTemplate(String type){
        EmailTemplate template = templates.get(type);
        if(template == null){
            throw new IllegalArgumentException("Template not found:" + type);
        }
        //Clone instead of returning the original object
        // This prevents modifications to the original prototype
        return template.clone();
    }
}

//CLIENT
public class PrototypePattern {
    static void main(String[] args) {
        EmailTemplate welcomeEmail = EmailTemplateRegistry.getTemplate("Welcome");
        welcomeEmail.setContent("type - 1");
        welcomeEmail.send("user1@gmail.com");
        System.out.println(welcomeEmail);

        EmailTemplate welcomeEmail2 = EmailTemplateRegistry.getTemplate("Welcome");
        welcomeEmail2.setContent("type - 2");
        welcomeEmail2.send("user@gmail.com");
        System.out.println(welcomeEmail2);
    }
}
/*
Pros:
1. Avoids expensive object creation(cloning existing objects can be cheaper)
   -> database calls
   -> network calls
   -> complex calculating
   -> large initializations
   -> passing / configurations
2. Faster object creation.
3. Reduces subclassing (Map<String, EmailTemplate>).
4. Runtime object configuration.
5. Good for creating similar objects

Cons:
1. Deep cloning can be complicated
    if your object contains nested objects:
    class order{
    Customer customer;
    Address address;
    List<Item> items;
    }
    simply using super.clone();
    doesn't automatically create independent copies of all nested mutable objects.
    you may need deep copying.
2. Circular reference can make cloning difficult.(A-> B -> C -> D)
3. Clone implementation can be complex
4. Java cloneable mechanism has limitations alternatives include:
   copy constructor
   factory
   serialization
   custom copy method
   depending on the situation

When to use:
1. Object creation is expensive
2. You need many similar objects
3. Objects differ only slightly
4. You want runtime-defined prototypes

When to avoid:
1. Object creation is cheap
2. Object has complex mutable nested state
3. Objects aren't similar
4. You only create a few objects
 */