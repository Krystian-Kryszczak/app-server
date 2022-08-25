package app.server.storage.repository.notification;

import app.server.model.notification.Notification;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.ExtendedMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.descending;

@Singleton
public class MongoDbNotificationRepository extends ExtendedMongoDbRepository<Notification> implements NotificationRepository {
    public MongoDbNotificationRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getNotificationCollection(), Notification.class);
    }
    @Override
    public Flux<Notification> findByTargetId(String targetHexId) {
        return Flux.from(getCollection().find(getBsonEq_targetId(targetHexId)).sort(descending("dateTime")));
    }
    @Override
    public Flux<Document> findDocByTargetId(String targetHexId) {
        return Flux.from(getDocCollection().find(getBsonEq_targetId(targetHexId)).limit(12).sort(descending("dateTime")));
    }
    private Bson getBsonEq_targetId(String targetHexId) {
        return eq("targetHexId",  new ObjectId(targetHexId));
    }
}