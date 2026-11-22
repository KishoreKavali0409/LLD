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
