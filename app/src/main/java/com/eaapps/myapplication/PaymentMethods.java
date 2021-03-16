package com.eaapps.myapplication;

public class PaymentMethods {

    int id;
    int paymentActive;
    int paymentDeActive;

    public PaymentMethods(int id, int paymentActive, int paymentDeActive) {
        this.id = id;
        this.paymentActive = paymentActive;
        this.paymentDeActive = paymentDeActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaymentActive() {
        return paymentActive;
    }

    public void setPaymentActive(int paymentActive) {
        this.paymentActive = paymentActive;
    }

    public int getPaymentDeActive() {
        return paymentDeActive;
    }

    public void setPaymentDeActive(int paymentDeActive) {
        this.paymentDeActive = paymentDeActive;
    }
}
