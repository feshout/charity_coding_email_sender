package com.codecool.charity.mail;

import com.codecool.charity.send.Send;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Controller
public class EmailController {

    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailController(EmailSender emailSender,
                           TemplateEngine templateEngine){
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @ResponseBody
    public String send(Send send) {
        Context context = new Context();
        context.setVariable("header", send.getSender().getHostName());
        context.setVariable("title", send.getTemplate().getTitle());
        context.setVariable("description", send.getTemplate().getDescription());

        String body = templateEngine.process("template", context);
        emailSender.sendEmail(send);
        return body;
    }
}