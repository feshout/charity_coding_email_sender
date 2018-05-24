package com.codecool.charity.send;

import com.codecool.charity.senders.SenderService;
import com.codecool.charity.templates.TemplateService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendService {
    
    private JavaMailSender sender;
    private SenderService senderService;
    private TemplateService templateService;
    
    public SendService(JavaMailSender sender,
                       SenderService senderService,
                       TemplateService templateService) {
        this.sender = sender;
        this.senderService = senderService;
        this.templateService = templateService;
    }

    public void sendEmail(Send send, String body) {

        MimeMessage mail = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(send.getReceiver());
            helper.setReplyTo(send.getReceiver());
            helper.setFrom(send.getSender().getHostName());
            helper.setSubject(send.getTemplate().getTitle());
            helper.setText("template", true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        sender.send(mail);
    }

    public Send createSend(SendForm form){
        Send send = new Send();

        send.setReceiver(form.getTo());
        send.setSender(senderService.findOne(1L));
        send.setTemplate(templateService.findOne(form.getTemplateId()));

        return send;
    }
}
