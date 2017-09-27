package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Globals;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint("/subscribe")
public class SubscriptionSocket {

    public static final Logger LOGGER = Logger.getLogger(SubscriptionSocket.class.getName());

    @OnOpen
    public void onOpen(final Session session) throws IOException {
        LOGGER.info("Connection opened, session id = " + session.getId());
        LOGGER.info("Got subscription from -> " + session.getRequestParameterMap().get("subscriber"));
        LOGGER.info("Got subscription for  -> " + session.getRequestParameterMap().get("subscribedTo"));
        LOGGER.info("Registering current session as subscription");
        registerSession(new Subscription(session.getRequestParameterMap().get("subscriber").get(0),
                session.getRequestParameterMap().get("subscribedTo").get(0)), session);
        session.getBasicRemote().sendText("You were successfully subscribed");
    }

    private void registerSession(final Subscription subscription, final Session session) {
        Subscriptions.subscriptionSessionMap.put(subscription, session);
        LOGGER.info("Registered session with session id = [" + session.getId() + "]");
        LOGGER.info(subscription.toString());
    }

    @OnMessage
    public void onMessage(final Session session, final String message) {
        LOGGER.info("Received message from " + session.getId());
        LOGGER.info("Message is \"" + message + "\"");
        final String[] requests = message.split(";");
        if (requests[0].equals("subscribe")) {
            LOGGER.info(session.getId() + " sent a subscription request");
            final Subscription subscription = Globals.GSON.fromJson(requests[1], Subscription.class);
            Subscriptions.subscriptionSessionMap.put(subscription, session);
            LOGGER.info("Subscribed : " + subscription);
        }
    }

    @OnError
    public void onError(final Session session, final Throwable throwable) {
        LOGGER.log(Level.SEVERE, "Some error occurred with " + session.getId(), throwable);
    }

    @OnClose
    public void onClose(final Session session) {
        LOGGER.info("Closing connection with " + session.getId());
    }
}
