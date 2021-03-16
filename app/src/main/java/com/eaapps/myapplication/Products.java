package com.eaapps.myapplication;

public class Products {

    int id;
    int number;
    String title;
    String description;
    int imageRes;
    double total;

    public Products(int id, int number, String title, String description, int imageRes, double total) {
        this.id = id;
        this.number = number;
        this.title = title;
        this.description = description;
        this.imageRes = imageRes;
        this.total = total;
    }

    public Products(int id, String title, String description, int imageRes, double total) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageRes = imageRes;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
