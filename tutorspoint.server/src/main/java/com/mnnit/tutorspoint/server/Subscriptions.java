package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Subscription;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

public class Subscriptions {
    public static final ConcurrentHashMap<Subscription, Session> subscriptionSessionMap = new ConcurrentHashMap<>();
}
