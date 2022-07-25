package app.server.model.comment;

import app.server.model.being.user.User;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;


@Introspected
public class Comment {
    @Nullable
    @BsonId
    final ObjectId id;
    @NonNull
    @BsonProperty("user")
    final User user;
    @NonNull @NotBlank
    @BsonProperty("exhibit")
    final String exhibitId;
    @NonNull
    @NotBlank
    @BsonProperty("content")
    final String content;
    @NonNull
    @BsonProperty("datetime")
    final LocalDateTime dateTime;
    @BsonProperty("rating")
    int rating;
    @Nullable
    @BsonProperty("nodeHexId")
    final String nodeHexId;
    @Creator
    @BsonCreator
    public Comment(@Nullable @BsonId ObjectId id, @NonNull @BsonProperty("user") User user, @NonNull @NotBlank @BsonProperty("exhibit") String exhibitId,
               @NonNull @NotBlank @BsonProperty("content") String content, @NonNull @BsonProperty("datetime") LocalDateTime dateTime,
               @BsonProperty("rating") int rating, @Nullable @BsonProperty("nodeHexId") String node) {
        this.id = id;
        this.user = user;
        this.exhibitId = exhibitId;
        this.content = content;
        this.dateTime = dateTime;
        this.rating = rating;
        if (node!=null && node.isBlank()) node = null;
        this.nodeHexId = node;
    }
    private Comment(@NonNull User user, @NonNull @NotBlank String exhibitId, @NonNull @NotBlank String content) {
        this(user, exhibitId, content, null);
    }
    private Comment(@NonNull User user, @NonNull @NotBlank String exhibitId, @NonNull @NotBlank String content, @Nullable String node) {
        this.id = null;
        this.user = user;
        this.exhibitId = exhibitId;
        this.content = content;
        this.dateTime = LocalDateTime.now();
        this.rating = 0;
        if (node!=null && node.isBlank()) node = null;
        this.nodeHexId = node;
    }
    public static Comment factory(@NonNull User user, @NonNull @NotBlank String exhibitId, @NonNull @NotBlank String content, @NonNull @NotBlank String node) {
        return new Comment(user, exhibitId, content, node);
    }
    public static Comment factory(@NonNull User user, @NonNull @NotBlank String exhibitId, @NonNull @NotBlank String content) {
        return new Comment(user, exhibitId, content);
    }
    @Nullable
    public ObjectId getId() {
        return id;
    }
    public Publisher<Boolean> report(@NonNull User user, @NonNull String content) {
        return null;
    }
    public Publisher<Boolean> reportToAdmin(@NonNull User user, @NonNull String content) {
        return null;
    }
    public Publisher<List<String>> getHistory(@NonNull User user) {
        return null;
    }
    @NonNull
    public User getUser() {
        return user;
    }
    public int getRating() {
        return rating;
    }
    public Publisher<String> editComment(@NonNull User user, @NonNull String hexId, @NonNull String newContent) {
        return null;
    }
    public Publisher<Boolean> removeComment(@NonNull User user, @NonNull String hexId) {
        return null;
    }
    public Publisher<Boolean> hideComment(@NonNull User user, @NonNull String hexId) {
        return null;
    }
    public String getUrl() {
        return null;
    }
    public Publisher<Boolean> voteUp(@NonNull MongoCollection<?> collection, @NonNull User user) { // TODO
        return null; // TODO
    }
    public Publisher<Boolean> voteDown(@NonNull MongoCollection<?> collection, @NonNull User user) { // TODO
        return null; // TODO
    }
    public Publisher<Boolean> cancelVote(@NonNull MongoCollection<?> collection, @NonNull User user) { // TODO
        return null; // TODO
    }
    @NonNull
    public String getContent() {
        return content;
    }
    @NonNull
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public Publisher<Boolean> addNodeComment(@NonNull Comment comment) { // TODO
        return null; // TODO
    }
}