package app.server.storage.repository.being.user.profile;

import app.server.model.being.user.profile.UserProfile;
import app.server.model.exhibit.ExhibitType;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.ExtendedMongoDbRepository;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;
import org.bson.Document;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.*;

@Singleton
public class MongoDbUserProfileRepository extends ExtendedMongoDbRepository<UserProfile> implements UserProfileRepository {
    public MongoDbUserProfileRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getUserProfileCollection(), UserProfile.class);
    }
    public Mono<UserProfile> findByUserId(String userHexId) {
        return Mono.from(getCollection().find(eq("userHexId", userHexId)));
    }
    public Mono<Document> findDocByUserId(String userHexId) {
        return Mono.from(getDocCollection().find(eq("userHexId", userHexId)));
    }
    public Flux<String> getFollowedForUserByHexId(String userHexId, int limit, int skip) {
        return Mono.from(getDocCollection().find(eq("userHexId", userHexId)).projection(Projections.fields(eq("followed"))).first())
            .map(doc -> doc.getList("followed", String.class, List.of())).flatMapMany(Flux::fromIterable).limitRate(limit).skip(skip);
    }
    public Mono<Boolean> addToFollowedForUserByHexId(String userHexId, String followUserHexId) {
        return Mono.from(getDocCollection().findOneAndUpdate(eq("userHexId", userHexId), Updates.addToSet("followed", followUserHexId)))
            .map(doc -> doc.getList("followed", String.class, List.of()).contains(followUserHexId));
    }
    public Mono<Boolean> removeToFollowedForUserByHexId(String userHexId, String followUserHexId) {
        return Mono.from(getDocCollection().findOneAndUpdate(eq("userHexId", userHexId), Updates.pull("followed", followUserHexId)))
            .map(doc -> !doc.getList("followed", String.class, List.of()).contains(followUserHexId));
    }
    public Flux<String> getExhibitsProfile(String userHexId, int limit, int skip) {
        return Mono.from(getDocCollection().find(eq("userHexId", userHexId)).projection(Projections.fields(eq("exhibits"))))
            .map(doc -> doc.getList("exhibits", String.class, List.of())).flatMapMany(Flux::fromIterable).limitRate(limit).skip(skip);
    }
    public Mono<Boolean> shareOnProfile(String userHexId, ExhibitType type, String exhibitHexId) {
        return Mono.from(getDocCollection().findOneAndUpdate(eq("userHexId", userHexId), Updates.addToSet("exhibits", exhibitHexId+":"+type.getUrlModifier())))
            .map(doc -> doc.getList("exhibits", String.class, List.of()).contains(exhibitHexId+":"+type.getUrlModifier()));
    }
    public Mono<Boolean> addToProfile(String userHexId, ExhibitType type, String exhibitHexId) {
        return Mono.from(getDocCollection().findOneAndUpdate(eq("userHexId", userHexId), Updates.addToSet("exhibits", exhibitHexId+":"+type.getUrlModifier())))
            .map(doc -> doc.getList("exhibits", String.class, List.of()).contains(exhibitHexId+":"+type.getUrlModifier()));
    }
    public Mono<Boolean> deleteFromProfile(String userHexId, String exhibitHexId) {
        return Mono.from(getDocCollection().findOneAndUpdate(eq("userHexId", userHexId), Updates.pullByFilter(Filters.regex("exhibits", Pattern.compile("^"+Pattern.quote(exhibitHexId), Pattern.CASE_INSENSITIVE)))))
            .map(doc -> doc.getList("exhibits", String.class, List.of()).stream().noneMatch(item -> item.startsWith(exhibitHexId)));
    }
}