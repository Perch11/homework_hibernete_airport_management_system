package com.airport.model;

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
        if (id <= 0) {
            throw new IllegalArgumentException("'id' must be positive number: ");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Passed null or empty value as 'name': ");
        }
        this.name = name;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(final Date foundDate) {
        if (foundDate == null) {
            throw new NullPointerException("Passed null value as 'foundDate': ");
        }
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