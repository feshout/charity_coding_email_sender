package com.codecool.charity.send;

import com.codecool.charity.form.EditForm;
import com.codecool.charity.form.LoginForm;
import com.codecool.charity.form.SendForm;
import com.codecool.charity.templates.Template;
import com.codecool.charity.templates.TemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;


@Controller
@RequestMapping(path = "/send")
public class SendController {

    private SendService sendService;
    private TemplateService templateService;

    public SendController(SendService sendService, TemplateService templateService) {
        this.sendService = sendService;
        this.templateService = templateService;
    }

    @GetMapping("/")
    public String displayForm(Model model){

        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("isConnected", sendService.isConnected());
        this.sendService.displayForm(model);
        return "sender/sendForm";
    }

    @PostMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm) {

        this.sendService.setUser(loginForm);
        return "redirect:/send/";
    }

    @GetMapping("/edit")
    public String editSend(@ModelAttribute SendForm form, Model model){


        Set<String> fields = sendService.getContentFields(templateService.findOne(form.getTemplateId()));
        EditForm editForm = new EditForm();
        model.addAttribute("fields", fields);
        model.addAttribute("editForm", editForm);
        model.addAttribute("temp", templateService.findOne(form.getTemplateId()));
        model.addAttribute("sendTo", form.getTo());
        return "sender/editForm";
    }

    @PostMapping("/edit")
    public String fillFields(@ModelAttribute EditForm form, Model model, @RequestParam Map<String, String> params) {

        EditForm filledForm = sendService.fillContent(form, params);
        this.sendService.sendEmail(filledForm, model);

        return "sender/result";
    }

    @PostMapping("/")
    public String sendEmail(@ModelAttribute EditForm form, Model model){
        this.sendService.sendEmail(form, model);
        return "sender/result";
    }

}
