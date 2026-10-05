class User {
    private String name;
    private String email;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }
}

class UserRepository {
    void save(User user) {
        System.out.println("Saving user: " + user.getName());
    }
}

class EmailService {
    void sendWelcomeEmail(User user) {
        System.out.println("Welcome email sent to: " + user.getEmail());
    }
}

public class SingleResponsilibilityPrinciple {
    public static void main(String[] args) {
        User user = new User("Kishore", "kishore@gmail.com");

        UserRepository repository = new UserRepository();
        EmailService emailService = new EmailService();

        repository.save(user);
        emailService.sendWelcomeEmail(user);
    }
}