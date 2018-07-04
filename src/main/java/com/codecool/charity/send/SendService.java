package com.codecool.charity.send;

import com.codecool.charity.form.EditForm;
import com.codecool.charity.form.LoginForm;
import com.codecool.charity.form.SendForm;
import com.codecool.charity.templates.Template;
import com.codecool.charity.templates.TemplateService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;


@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SendService {

    private String eMail;
    private boolean isConnected = false;
    private JavaMailSenderImpl javaMailSender;
    private TemplateService templateService;
    private TemplateEngine templateEngine;
    private SendRepository repository;

    public SendService(JavaMailSenderImpl javaMailSender,
                       TemplateService templateService, TemplateEngine templateEngine, SendRepository repository) {
        this.javaMailSender = javaMailSender;
        this.templateService = templateService;
        this.templateEngine = templateEngine;
        this.repository = repository;
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
        this.repository.save(send);
        model.addAttribute("message", "Message sent with success");
    }

    void displayForm(Model model) {

        SendForm sendForm = new SendForm();
        model.addAttribute("sendForm", sendForm);
        model.addAttribute("temps", templateService.getAll());
    }

    private void prepareAndSendEmail(Send send, String body) {

        MimeMessage mail = javaMailSender.createMimeMessage();
        setMimeMessageHelper(mail, send, body);
        javaMailSender.send(mail);
    }

    private void setMimeMessageHelper(MimeMessage mail, Send send, String body) {
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(send.getReceiver());
            helper.setReplyTo(eMail);
            helper.setSubject(send.getTemplate().getTitle());
            helper.setText(body, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void setUser(LoginForm loginForm) {

            javaMailSender.setUsername(loginForm.getEmail());
            javaMailSender.setPassword(loginForm.getPassword());

            if(verifyLoginAndPassword(loginForm)) {
                isConnected = true;
            }
    }

    private boolean verifyLoginAndPassword(LoginForm loginForm) {
        try {
            javaMailSender.testConnection();
        } catch (MessagingException e) {
            System.out.println("WRONG DATA PROVIDED");
            return false;
        }
        this.eMail = loginForm.getEmail();
        System.out.println("CORRECT DATA PROVIDED");
        return true;
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
        Template temp = new Template(form.getHeader(),
                form.getTitle(),
                form.getDescription()
        );

        send.setReceiver(form.getTo());
        send.setTemplate(temp);
        send.setDate(new Date());

        return send;
    }

    public void updateModel(Model model, SendForm form){

        EditForm editForm = new EditForm();
        model.addAttribute("editForm", editForm);
        model.addAttribute("temp", templateService.findOne(form.getTemplateId()));
        model.addAttribute("sendTo", form.getTo());
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public boolean isConnected() {
        return isConnected;
    }
}
