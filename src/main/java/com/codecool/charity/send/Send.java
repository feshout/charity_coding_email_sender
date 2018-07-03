package com.codecool.charity.send;


import com.codecool.charity.templates.Template;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sent")
public class Send {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String receiver;
    private Date date;
    @Transient
    private Template template;

    public Long getId() {
        return id;
    }

    public String getReceiver() {
        return receiver;
    }

    public Template getTemplate() {
        return template;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

