package com.codecool.charity.form;

public class SendForm {

    private String to;

    private Long templateId;

    public SendForm() {
    }

    public SendForm(String to, Long templateId) {
        this.to = to;
        this.templateId = templateId;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
}
