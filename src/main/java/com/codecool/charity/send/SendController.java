package com.codecool.charity.send;

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

        this.sendService.displayForm(model);
        return "sender/sendForm";
    }

    @PostMapping("/")
    public String sendEmail(@ModelAttribute SendForm form, Model model){

        this.sendService.sendEmail(form, model);
        return "sender/result";
    }

}
