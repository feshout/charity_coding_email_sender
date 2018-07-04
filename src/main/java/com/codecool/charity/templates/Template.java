package com.codecool.charity.templates;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Date;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"ID", "header", "title", "description"}))
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String header;
    private String title;
    private String description;
    private Date updateDate;

    public Template() {
    }

    public Template(String header, String title, String description) {
        this.header = header;
        this.title = title;
        this.description = description;
    }

    public Template(String header, String title, String description, Date updateDate) {
        this.header = header;
        this.title = title;
        this.description = description;
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
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


    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    static Comparator<Template> sortByDate = (o1, o2) -> {

        if (o1.getUpdateDate().before(o2.getUpdateDate())){
            return 1;
        } else if (o1.getUpdateDate().after(o2.getUpdateDate())){
            return -1;
        } else {
            return 0;
        }
    };
}
