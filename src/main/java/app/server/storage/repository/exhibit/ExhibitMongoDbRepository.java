package app.server.storage.repository.exhibit;

import app.server.model.exhibit.Exhibit;
import app.server.model.exhibit.ExhibitType;
import app.server.service.being.community.group.GroupService;
import app.server.service.being.user.UserService;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.votable.MongoDbVotableRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Singleton
public abstract class ExhibitMongoDbRepository<T extends Exhibit<T>> extends MongoDbVotableRepository<T> implements ExhibitRepository<T> {
    @Inject
    static UserService userService;
    @Inject
    static GroupService groupService;
    final Class<T> clazz;
    public ExhibitMongoDbRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<T> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
        this.clazz = clazz;
    }
    // ---------------------------------------------------------------------------------------------------- //
    public Flux<T> getFeedForUser(String userHexId, int limit, int skip) {
        if (!ObjectId.isValid(userHexId)) return Flux.empty();
        return userService.getUserFriendsHexIds(userHexId).collect(Collectors.toList())
            .flatMap(list -> userService.getDocUserProfile(list.get(new Random().nextInt(list.size()))))
                .map(doc -> doc.getList("exhibits", String.class).stream().filter(exhibit -> exhibit.endsWith(":"+Objects.requireNonNull(ExhibitType.getTypeByClass(clazz)).getUrlModifier())))
                    .map(exhibit -> exhibit.map(str -> str.split(":")[0]))
                        .flatMapMany(Flux::fromStream)
                            .flatMap(exhibitId -> Mono.from(findById(exhibitId)));
    }
    public Mono<Boolean> uploadByUser(T exhibit, String clientHexId) {
        if (!ObjectId.isValid(clientHexId)||!exhibit.getUserHexId().equals(clientHexId)) return Mono.just(false);
        return Mono.from(getCollection().insertOne(exhibit)).thenReturn(true).onErrorReturn(false);
    }
}