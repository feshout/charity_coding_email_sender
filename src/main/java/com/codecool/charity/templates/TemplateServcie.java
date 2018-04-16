package com.codecool.charity.templates;

public interface TemplateServcie {

    Template getOne(int id);

    Iterable<Template> getAll();

    void save(Template template);
}
