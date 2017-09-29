package com.mnnit.tutorspoint.server;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NotificationThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(NotificationThread.class.getName());

    {
        setPriority(MAX_PRIORITY);
        setUncaughtExceptionHandler((t, e) -> {
            LOGGER.log(Level.SEVERE, " Some exception occurred in notifier thread. ", e);
        });
    }

    @Override
    public void run() {
        while (true) {
            Subscriptions.notificationSessionMap.forEach((notification, session) -> {
                if (!notification.isSent() && session.isOpen()) {
                    session.getAsyncRemote().sendText(notification.getMessage());
                }
            });
        }
    }
}
