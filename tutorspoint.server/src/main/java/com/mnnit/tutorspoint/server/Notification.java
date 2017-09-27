package com.mnnit.tutorspoint.server;

public class Notification {
    private Subscription subscription;
    private String message;
    private boolean isSent;

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
}
