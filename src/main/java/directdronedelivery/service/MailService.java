package directdronedelivery.service;

public interface MailService {
    public boolean sendEmail(String messageSubject,String messageText, String recipientEmail);
}
