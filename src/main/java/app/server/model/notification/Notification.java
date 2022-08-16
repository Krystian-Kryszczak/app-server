package app.server.model.notification;

import app.server.model.notification.type.NotificationType;

import java.time.LocalDateTime;

public class Notification {
    final NotificationType type;
    final String imageUrl;
    final String content;
    final LocalDateTime dateTime = LocalDateTime.now();
    public Notification(NotificationType type, String imageUrl, String content) {
        this.type = type;
        this.imageUrl = imageUrl;
        this.content = content;
    }
}