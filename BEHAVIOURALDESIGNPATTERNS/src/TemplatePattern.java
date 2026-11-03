abstract class NotificationSender{
    //Final templatemethod
    public final void send(String to, String rawMessage){
        rateLimitCheck(to);
        validateRecipient(to);
        String formatted = formatMessage(rawMessage);
        preSendAuditLog(to, formatted);
        // Logic not common
        String composedMessage = composeMessage(formatted);
        sendMessage(to, composedMessage);
        // Common
        postSendAudit(to);
    }
    //common step 1
    private void rateLimitCheck(String to){
        System.out.println("Checking rate limit for:" + to);
    }
    //common step 2
    private void validateRecipient(String to){
        System.out.println("Checking rate limit for:" + to);
    }
    //common step 3
    private String formatMessage(String message){
        return message.trim();
    }
    //common step 4
    private void preSendAuditLog(String to, String message){
        System.out.println("Logging before send" + message + "to" + to);
    }
    //hooks for subclass
    protected abstract String composeMessage(String formattedMessage);
    protected abstract void sendMessage(String to, String message);
    // Step 5 (optional hooks)
    protected void postSendAudit(String to){
        System.out.println("Analytics updated for" + to);
    }
}
class EmailNotification extends NotificationSender{
    @Override
    protected String composeMessage(String formattedMessage) {
        return "<html><body><p>" + formattedMessage + "</p></body></html>";
    }

    @Override
    protected void sendMessage(String to, String message) {
        System.out.println("Sending Email to" + to + "with content: \n" + message);
    }
}
class SMSNotification extends NotificationSender{
    @Override
    protected String composeMessage(String formattedMessage) {
        return "[SMS]" + formattedMessage;
    }

    @Override
    protected void sendMessage(String to, String message) {
        System.out.println("Sending SMS to " + to + "with msg" + message);
    }
}
public class TemplatePattern {
    static void main(String[] args) {
        NotificationSender emailSender = new EmailNotification();
        emailSender.send("john@example.com", "Hi how are you ");
        NotificationSender smsSender = new SMSNotification();
        smsSender.send("1234567890", "Your otp is 34567");
    }
}
