package com.codecool.charity.send;

import com.codecool.charity.senders.SenderService;
import com.codecool.charity.templates.TemplateService;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendService {

    private JavaMailSenderImpl javaMailSender;
    private SenderService senderService;
    private TemplateService templateService;

    public SendService(JavaMailSenderImpl javaMailSender,
                       SenderService senderService,
                       TemplateService templateService) {
        this.javaMailSender = javaMailSender;
        this.senderService = senderService;
        this.templateService = templateService;
    }

    public void sendEmail(Send send, String body) {

        javaMailSender.setUsername(send.getSender().getHostName());
        javaMailSender.setPassword(send.getSender().getPassword());
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(send.getReceiver());
            helper.setReplyTo(send.getSender().getHostName());
            helper.setSubject(send.getTemplate().getTitle());
            helper.setText(body, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mail);
    }

    public Send createSend(SendForm form){
        Send send = new Send();

        send.setReceiver(form.getTo());
        send.setSender(senderService.findOne(4L));
        send.setTemplate(templateService.findOne(form.getTemplateId()));

        return send;
    }
}
