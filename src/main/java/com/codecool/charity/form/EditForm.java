package com.codecool.charity.form;

public class EditForm {

    private String header;
    private String title;
    private String description;
    private String to;

    public EditForm() {
    }

    public EditForm(String header, String title, String description, String to) {

        this.header = header;
        this.title = title;
        this.description = description;
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
