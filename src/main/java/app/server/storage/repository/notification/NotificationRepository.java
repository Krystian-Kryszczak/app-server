package app.server.storage.repository.notification;

import app.server.model.notification.Notification;
import app.server.storage.repository.Repository;
import org.bson.Document;
import reactor.core.publisher.Flux;

public interface NotificationRepository extends Repository<Notification> {
    Flux<Notification> findByTargetId(String targetHexId);
    Flux<Document> findDocByTargetId(String targetHexId);
}