package com.codecool.charity.templates;

public interface TemplateServcie {

    String getOne(int id);

    Iterable<Template> getAll();

    void save(Template template);
}
