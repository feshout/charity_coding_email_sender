package com.codecool.charity.send;

import com.codecool.charity.templates.TemplateServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Controller
@RequestMapping(path = "/send")
public class SendController {

    private TemplateServiceImpl templateService;
    private SendService sendService;
    private TemplateEngine templateEngine;

    public SendController(TemplateServiceImpl templateService,
                          SendService sendService,
                          TemplateEngine templateEngine) {
        this.templateService = templateService;
        this.sendService = sendService;
        this.templateEngine = templateEngine;
    }

    @GetMapping("/")
    public String displayForm(Model model){

        SendForm sendForm = new SendForm();
        model.addAttribute("sendForm", sendForm);
        model.addAttribute("temps", templateService.getAll());

        return "sender/sendForm";
    }

    @PostMapping("/")
    public String sendEmail(@ModelAttribute SendForm form, Model model){

        Send send = sendService.createSend(form);

        Context context = new Context();
        context.setVariable("header", send.getTemplate().getHeader());
        context.setVariable("title", send.getTemplate().getTitle());
        context.setVariable("description", send.getTemplate().getDescription());

        String body = templateEngine.process("display", context);
        try {
            this.sendService.sendEmail(send, body);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("message", "Message sent with success");

        return "sender/result";

    }

}
