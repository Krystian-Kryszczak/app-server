package app.server.model.being.group;

import app.server.model.being.Being;
import app.server.model.notification.Notification;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;

public class Group extends Being {
    public Group(ObjectId id, String name) {
        super(id);
    }
    Publisher<Boolean> setRules() { // TODO group rules
        return null; // TODO
    }
    Publisher<String> getRules() { // TODO group rules
        return null; // TODO
    }
    Publisher<String> sendNotification(Notification notification) {
        return null; // TODO
    }
    Publisher<Boolean> deleteNotification(ObjectId id) {
        return null; // TODO
    }
}