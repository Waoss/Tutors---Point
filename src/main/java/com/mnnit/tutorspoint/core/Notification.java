package com.mnnit.tutorspoint.core;

public class Notification {
    private Subscription subscription;
    private String message;
    private boolean isSent;

    public Notification() {
    }

    public Notification(final Subscription subscription, final String message, final boolean isSent) {
        this.subscription = subscription;
        this.message = message;
        this.isSent = isSent;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(final Subscription subscription) {
        this.subscription = subscription;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(final boolean sent) {
        isSent = sent;
    }

    @Override
    public String toString() {
        return "Notification{" +
                ", subscription=" + subscription +
                ", message='" + message + '\'' +
                ", isSent=" + isSent +
                '}';
    }
}
