package com.codecool.charity.send;

import com.codecool.charity.form.EditForm;
import com.codecool.charity.form.SendForm;
import com.codecool.charity.senders.SenderService;
import com.codecool.charity.templates.Template;
import com.codecool.charity.templates.TemplateService;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendService {

    private JavaMailSenderImpl javaMailSender;
    private SenderService senderService;
    private TemplateService templateService;
    private TemplateEngine templateEngine;

    public SendService(JavaMailSenderImpl javaMailSender,
                       SenderService senderService,
                       TemplateService templateService, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.senderService = senderService;
        this.templateService = templateService;
        this.templateEngine = templateEngine;
    }

    void sendEmail(EditForm form, Model model) {
        Send send = createSend(form);
        Context context = getContext(send);
        String body = templateEngine.process("display", context);

        try {
            prepareAndSendEmail(send, body);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("message", "Message sent with success");
    }

    void displayForm(Model model) {

        SendForm sendForm = new SendForm();
        model.addAttribute("sendForm", sendForm);
        model.addAttribute("temps", templateService.getAll());
    }

    private void prepareAndSendEmail(Send send, String body) {

        setUser(send);
        MimeMessage mail = javaMailSender.createMimeMessage();
        setMimeMessageHelper(mail, send, body);
        javaMailSender.send(mail);
    }

    private void setMimeMessageHelper(MimeMessage mail, Send send, String body) {
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(send.getReceiver());
            helper.setReplyTo(send.getSender().getHostName());
            helper.setSubject(send.getTemplate().getTitle());
            helper.setText(body, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void setUser(Send send) {
        javaMailSender.setUsername(send.getSender().getHostName());
        javaMailSender.setPassword(send.getSender().getPassword());
    }


    private Context getContext(Send send) {
        Context context = new Context();
        context.setVariable("header", send.getTemplate().getHeader());
        context.setVariable("title", send.getTemplate().getTitle());
        context.setVariable("description", send.getTemplate().getDescription());

        return context;
    }

    private Send createSend(EditForm form){
        Send send = new Send();
        Template temp = new Template(form.getHeader(), form.getTitle(), form.getDescription());

        send.setReceiver(form.getTo());
        send.setSender(senderService.findOne(5L));
        send.setTemplate(temp);

        return send;
    }

    public void updateModel(Model model, SendForm form){

        EditForm editForm = new EditForm();
        model.addAttribute("editForm", editForm);
        model.addAttribute("temp", templateService.findOne(form.getTemplateId()));
        model.addAttribute("sendTo", form.getTo());
    }

}
