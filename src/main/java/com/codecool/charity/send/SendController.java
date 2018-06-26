package com.codecool.charity.send;

import com.codecool.charity.form.EditForm;
import com.codecool.charity.form.SendForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/send")
public class SendController {

    private SendService sendService;

    public SendController(SendService sendService) {
        this.sendService = sendService;
    }

    @GetMapping("/")
    public String displayForm(Model model){
        this.sendService.setUser();
        this.sendService.displayForm(model);
        return "sender/sendForm";
    }

    @GetMapping("/edit")
    public String editSend(@ModelAttribute SendForm form, Model model){

        sendService.updateModel(model, form);
        return "sender/editForm";
    }

    @PostMapping("/")
    public String sendEmail(@ModelAttribute EditForm form, Model model){
        this.sendService.sendEmail(form, model);
        return "sender/result";
    }

}
