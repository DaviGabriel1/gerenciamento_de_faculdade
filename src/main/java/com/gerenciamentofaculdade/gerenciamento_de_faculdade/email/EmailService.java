package com.gerenciamentofaculdade.gerenciamento_de_faculdade.email;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmail(
            String to,
            String username,
            EmailTemplateName content,
            String confirmationUrl,
            String activationCode,
            String subject
    ) throws MessagingException;
}
