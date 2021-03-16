package com.eaapps.myapplication;

public class Cards {

    String number;
    int image;
    boolean selected=false;
    public Cards(String number, int image) {
        this.number = number;
        this.image = image;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}
