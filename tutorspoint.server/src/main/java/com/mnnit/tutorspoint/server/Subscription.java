package com.mnnit.tutorspoint.server;

public class Subscription implements Comparable<Subscription> {
    private String subscriber;
    private String subscribedTo;

    public Subscription(final String subscriber, final String subscribedTo) {
        this.subscriber = subscriber;
        this.subscribedTo = subscribedTo;
    }

    @Override
    public int compareTo(final Subscription that) {
        return subscriber.compareTo(that.subscriber) + subscribedTo.compareTo(that.subscribedTo);
    }

    @Override
    public int hashCode() {
        int result = getSubscriber() != null ? getSubscriber().hashCode() : 0;
        result = 31 * result + (getSubscribedTo() != null ? getSubscribedTo().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Subscription that = (Subscription) o;

        if (getSubscriber() != null ? !getSubscriber().equals(that.getSubscriber()) : that.getSubscriber() != null)
            return false;
        return getSubscribedTo() != null ? getSubscribedTo().equals(that.getSubscribedTo()) :
                that.getSubscribedTo() == null;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(final String subscriber) {
        this.subscriber = subscriber;
    }

    public String getSubscribedTo() {
        return subscribedTo;
    }

    public void setSubscribedTo(final String subscribedTo) {
        this.subscribedTo = subscribedTo;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "subscriber='" + subscriber + '\'' +
                ", subscribedTo='" + subscribedTo + '\'' +
                '}';
    }
}
