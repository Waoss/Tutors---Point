package com.mnnit.tutorspoint.net;

import com.mnnit.tutorspoint.core.Notification;

import java.io.IOException;
import java.net.URL;


public class NotificationTask extends GenericResponsiveTask<Notification[]> {
    Notification[] notifications;

    public NotificationTask(final String username) throws IOException {
        super(new URL(
                        System.getProperty("com.mnnit.tutorspoint.server.url") +
                                "/getNotificationsForUser?user=" + username),
                Notification[].class);
    }

    @Override
    protected Notification[] call() throws Exception {
        return notifications = super.call();
    }

    public Notification[] getNotifications() {
        return notifications;
    }

    public void setNotifications(final Notification[] notifications) {
        this.notifications = notifications;
    }
}
