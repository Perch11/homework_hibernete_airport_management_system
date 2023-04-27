package com.airport.model;

import com.airport.validator.Validator;

import java.sql.Date;

public class Company {

    private int id;
    private String name;
    private Date foundDate;

    public Company(final int id, final String name, final Date foundDate) {
        setId(id);
        setName(name);
        setFoundDate(foundDate);
    }

    public Company(final String name, final Date foundDate) {
        setName(name);
        setFoundDate(foundDate);
    }

    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        Validator.checkId(id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        Validator.validateString(name);
        this.name = name;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(final Date foundDate) {
        Validator.checkNull(foundDate);
        this.foundDate = foundDate;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foundDate=" + foundDate +
                "}\n";
    }
}