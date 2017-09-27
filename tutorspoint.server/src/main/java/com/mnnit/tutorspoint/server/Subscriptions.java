package com.mnnit.tutorspoint.server;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Subscriptions {
    public static final ConcurrentHashMap<Subscription, Session> subscriptionSessionMap = new ConcurrentHashMap<>();
    public static final ConcurrentLinkedQueue<String> notificationQueue = new ConcurrentLinkedQueue<>();
}
