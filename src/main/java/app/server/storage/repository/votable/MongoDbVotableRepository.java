package app.server.storage.repository.votable;

import app.server.model.StorageItem;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.ExtendedMongoDbRepository;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.reactivestreams.client.MongoClient;
import io.micronaut.core.annotation.NonNull;
import org.bson.Document;
import org.bson.conversions.Bson;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Filters.exists;

public abstract class MongoDbVotableRepository<T extends StorageItem> extends ExtendedMongoDbRepository<T> implements VotableRepository<T> {
    public MongoDbVotableRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient, String collectionName, Class<T> clazz) {
        super(mongoConf, mongoClient, collectionName, clazz);
    }
    // ---------------------------------------------------------------------------------------------------- //
    @NonNull
    public Mono<Integer> setUserVote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String targetHexId, boolean up) {
        Bson bsonId = getBsonEq_id(targetHexId); String voteList = up ? "up" : "down", oppositeVoteList = !up ? "up" : "down";
        return Mono.from(getDocCollection().find(bsonId).projection(Projections.fields(elemMatch(voteList, exists(userHexId)))).first()) // check if exhibit voteList contains userHexId
                .then( // if user vote value is the same return actual rating
                        Mono.from(getDocCollection().find(bsonId).projection(new Document("rating", 1)))
                                .map(doc -> doc.getInteger("rating")).switchIfEmpty(Mono.just(0))
                ).switchIfEmpty(Mono.from(getDocCollection().find(bsonId).projection(Projections.fields(elemMatch(oppositeVoteList, exists(userHexId)))).first()) // else check this
                        .then( // if user vote opposite option (!up list contains userHexId)
                                Mono.from(getDocCollection().findOneAndUpdate(bsonId, Updates.pull(oppositeVoteList, userHexId))) // remove userHexId from opposite list
                                        .then(Mono.from(getDocCollection().findOneAndUpdate(bsonId, // add userHexId to vote list & double (de)increment
                                                Updates.combine(Updates.addToSet(voteList, userHexId), Updates.inc("rating", up ? 2 : -2),
                                                        Projections.fields(eq("rating")) // get only rating field in return document
                                                ))
                                        ).map(doc -> doc.getInteger("rating", 0))) // Then get rating value)
                        ).switchIfEmpty(Mono.from(getDocCollection().findOneAndUpdate(bsonId,
                                Updates.combine(Updates.addToSet(voteList, userHexId), Updates.inc("rating", up ? 1 : -1), //add userHexId to list & increment
                                        Projections.fields(eq("rating")) // get only rating field in return document
                                ))
                        ).map(updatedDoc -> updatedDoc.getInteger("rating", 0))) // Then get rating value
                );
    }
    @NonNull
    public Mono<Integer> unsetUserVote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String targetHexId) {
        Bson bsonId = getBsonEq_id(targetHexId);
        return Mono.from(getDocCollection().findOneAndUpdate(bsonId, Updates.pull("up", userHexId))) // remove userHexId from opposite list
                .then(Mono.from(getDocCollection().findOneAndUpdate(bsonId, // add userHexId to vote list & double (de)increment
                        Updates.combine(Updates.pull("up", userHexId), Updates.inc("rating",-1),
                                Projections.fields(eq("rating")) // get only rating field in return document
                        ))).map(doc -> doc.getInteger("rating", 0))) // Then get rating value)
                .switchIfEmpty(Mono.from(getDocCollection().findOneAndUpdate(bsonId, // add userHexId to vote list & double (de)increment
                        Updates.combine(Updates.pull("down", userHexId), Updates.inc("rating",1),
                                Projections.fields(eq("rating")) // get only rating field in return document
                        ))).map(doc -> doc.getInteger("rating", 0))); // Then get rating value
    }
    @NonNull
    public Mono<Boolean> getVote(@NonNull @NotBlank String userHexId, @NonNull @NotBlank String targetHexId) {
        Bson bsonId = getBsonEq_id(targetHexId);
        return Mono.from(getDocCollection().find(bsonId).projection(Projections.fields(elemMatch("up", exists(userHexId)))).first())
                .thenReturn(true)
                .switchIfEmpty(Mono.from(getDocCollection().find(bsonId).projection(Projections.fields(elemMatch("down", exists(userHexId)))).first())
                        .thenReturn(false));
    }
}