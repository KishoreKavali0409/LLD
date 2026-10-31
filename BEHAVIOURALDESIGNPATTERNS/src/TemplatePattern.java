abstract class NotificationSender{
    public final void send(String to, String rawMessage){
        rateLimitCheck(to);
        validateRecipient(to);
        String formatted = formatMessage(rawMessage);
        preSendAuditLog(to, formatted);
    }
}
public class TemplatePattern {
}
