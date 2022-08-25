package app.server.storage.repository.comment;

import app.server.model.comment.Comment;
import app.server.storage.MongoDbConfiguration;
import app.server.storage.repository.votable.MongoDbVotableRepository;
import com.mongodb.client.model.Updates;
import com.mongodb.reactivestreams.client.MongoClient;
import jakarta.inject.Singleton;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.mongodb.client.model.Filters.eq;

@Singleton
public class MongoDbCommentRepository extends MongoDbVotableRepository<Comment> implements CommentRepository {
    public MongoDbCommentRepository(MongoDbConfiguration mongoConf, MongoClient mongoClient) {
        super(mongoConf, mongoClient, mongoConf.getCommentsCollection(), Comment.class);
    }
    public Flux<Comment> findByExhibitHexId(String hexId) {
        return Flux.from(getCollection().find(eq("exhibitHexId", hexId)));
    }
    public Mono<String> edit(String commentHexId, String newContent) {
        return Mono.from(getDocCollection().findOneAndUpdate(getBsonEq_id(commentHexId), Updates.set("content", newContent))).map(doc -> doc.getString("content"));
    }
    public Mono<Boolean> hide(String hexId) {
        return Mono.from(getDocCollection().findOneAndUpdate(getBsonEq_id(hexId), Updates.set("hidden", true))).map(doc -> doc.getBoolean("hidden"));
    }
    public Mono<Boolean> show(String hexId) {
        return Mono.from(getDocCollection().findOneAndUpdate(getBsonEq_id(hexId), Updates.unset("hidden"))).map(doc -> !doc.getBoolean("hidden", false));
    }
}