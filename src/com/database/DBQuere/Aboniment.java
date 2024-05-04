package com.database.DBQuere;

public class Aboniment {
    private String name;
    private String price;
    private String duration;

    public Aboniment(String name, String price, String duration) {
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }
}

