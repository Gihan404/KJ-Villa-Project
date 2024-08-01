package com.kayjay.villareservation;

public class Inquiry {
    private String title;
    private String type;
    private String details;

    public Inquiry(String title, String type, String details) {
        this.title = title;
        this.type = type;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDetails() {
        return details;
    }
}