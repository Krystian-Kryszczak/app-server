package app.server.storage.repository.media;

import app.server.model.media.Media;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.ExtendedMongoDbRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import static com.mongodb.client.model.Projections.include;

public abstract class MediaMongoDbRepository<T extends Media<T>> extends ExtendedMongoDbRepository<T> {
    public MediaMongoDbRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<T> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
    }
    public Publisher<Boolean> isPrivate(String hexId) {
        return Mono.from(getDocCollection().find(getBsonEq_id(hexId)).projection(include("isPrivate")).first())
            .map(doc -> doc.getBoolean("isPrivate", false));
    }
    public Publisher<Tuple2<Boolean, String>> getIsPrivateAndCreatorHexId(String hexId) {
        return Mono.from(getDocCollection().find(getBsonEq_id(hexId)).projection(include("isPrivate", "creatorHexId")).first())
            .map(doc -> Tuples.of(doc.getBoolean("isPrivate", false), doc.getString("creatorHexId")));
    }
}