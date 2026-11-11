//A class should have only one reason to change, if there are multiple responsibilities one change might break other
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

//User Repository
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
/*
Benefits :
- Improved Maintainability
- Better test coverage
- Lower risk in changes
- Reusable modules

Common Mistakes:
- Putting DB logic & business logic in the same class
- UI Code coupled with logic
 */