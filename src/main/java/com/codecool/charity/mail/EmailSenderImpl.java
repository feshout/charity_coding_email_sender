package com.codecool.charity.mail;

import com.codecool.charity.send.Send;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderImpl implements EmailSender {

    @Autowired
    private JavaMailSender sender;

    @Override
    public void sendEmail(Send send, String body) {

        MimeMessage mail = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(send.getTo());
            helper.setReplyTo("bylejakistrink");
            helper.setFrom("tubyłbylejakihostnejm");
            helper.setSubject(send.getTemplate().getTitle());
            helper.setText(body, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        sender.send(mail);
    }
}
