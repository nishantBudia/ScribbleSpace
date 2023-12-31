package com.nishant.ScribbleSpace.service;

import com.nishant.ScribbleSpace.util.MailingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailingService {

    @Autowired
    JavaMailSender mailSender;

    private final String host = "http://3.7.70.154:8080";

    private final String sender = System.getenv("AUTO_MAIL_SENDER_ADDRESS");

    public String sendUserVerificationEmail(String recipient, String token){
        SimpleMailMessage message = new SimpleMailMessage();

        try{
            message.setFrom(sender);
            message.setSubject("Verify Account");
            message.setTo(recipient);
            message.setText(MailingUtil.getUserVerificationEmailMessage(host,recipient,token));

            mailSender.send(message);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return "Success";
    }
}
