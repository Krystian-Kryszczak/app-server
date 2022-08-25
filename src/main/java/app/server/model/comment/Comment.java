package app.server.model.comment;

import app.server.model.StorageItem;
import app.server.model.being.user.User;
import app.server.model.exhibit.ExhibitType;
import app.server.service.comment.CommentService;
import app.server.service.history.comment.CommentHistoryService;
import app.server.service.report.ReportService;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Inject;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Introspected
public class Comment implements StorageItem {
    @Inject
    static ReportService reportService;
    @Inject
    static CommentService commentService;
    @Inject
    static CommentHistoryService commentHistoryService;
    @Nullable
    @BsonId
    final ObjectId id;
    @NonNull @Getter
    @BsonProperty("userHexId")
    final String userHexId;
    @Nullable
    @BsonProperty("type")
    final ExhibitType type;
    @Nullable @NotBlank
    @BsonProperty("exhibitHexId")
    final String exhibitHexId;
    @NonNull
    @NotBlank @Getter
    @BsonProperty("content")
    final String content;
    @NonNull @Getter
    @BsonProperty("datetime")
    final LocalDateTime dateTime;
    @BsonProperty("rating")
    int rating;
    @Nullable
    @BsonProperty("nodeHexId")
    final String nodeHexId;
    @Creator
    @BsonCreator
    public Comment(@Nullable @BsonId ObjectId id, @NonNull @BsonProperty("userHexId") String userHexId, @NonNull @BsonProperty("type") ExhibitType type, @NonNull @NotBlank @BsonProperty("exhibitHexId") String exhibitHexId,
            @NonNull @NotBlank @BsonProperty("content") String content, @NonNull @BsonProperty("datetime") LocalDateTime dateTime,
            @BsonProperty("rating") int rating, @Nullable @BsonProperty("nodeHexId") String node) {
        this.id = id;
        this.userHexId = userHexId;
        this.type = type;
        this.exhibitHexId = exhibitHexId;
        this.content = content;
        this.dateTime = dateTime;
        this.rating = rating;
        if (node!=null && node.isBlank()) node = null;
        this.nodeHexId = node;
        }
    public Comment(@NonNull String userHexId, @NonNull ExhibitType type, @NonNull @NotBlank String exhibitId, @NonNull @NotBlank String content) {
        this(userHexId, type, exhibitId, content, null);
    }
    public Comment(@NonNull String userHexId, @NonNull @NotBlank String content, @NonNull @NotBlank String node) {
        this.id = null;
        this.userHexId = userHexId;
        this.type = null;
        this.exhibitHexId = null;
        this.content = content;
        this.dateTime = LocalDateTime.now();
        this.rating = 0;
        this.nodeHexId = node;
    }
    public Comment(@NonNull String userHexId, @NonNull ExhibitType type, @NonNull @NotBlank String exhibitId, @NonNull @NotBlank String content, @Nullable String node) {
        this.id = null;
        this.userHexId = userHexId;
        this.type = type;
        this.exhibitHexId = exhibitId;
        this.content = content;
        this.dateTime = LocalDateTime.now();
        this.rating = 0;
        if (node!=null && node.isBlank()) node = null;
        this.nodeHexId = node;
    }
    @Nullable
    public ObjectId getId() {
        return id;
    }
    public Publisher<Boolean> report(@NonNull User user, @NonNull String content) {
        if (getId()==null) return Mono.just(false);
        return reportService.reportComment(getId(), type, user, content);
    }
    public Publisher<Boolean> reportToAdmin(@NonNull User user, @NonNull String content) {
        if (getId()==null) return Mono.just(false);
        return reportService.reportCommentToAdmin(getId(), type, user, content);
    }
    public Publisher<List<String>> getHistory(@NonNull User user) {
        if (getId()==null||user.getId()==null) return Mono.just(List.of());
        return commentHistoryService.getHistory(getId().toHexString(), user.getId().toHexString());
    }
    public int getRating() {
        return rating;
    }
    public Mono<String> edit(@NonNull User user, @NonNull String newContent) {
        return commentService.edit(user, getId(), newContent);
    }
    public String getUrl() {
        return getId()!=null&&type!=null ? "/"+type.getUrlModifier()+"/"+ exhibitHexId +"/comments/"+getId().toHexString() : "";
    }
    public Mono<Integer> voteUp(@NonNull User user) {
        return commentService.vote(user, this, true);
    }
    public Mono<Integer> voteDown(@NonNull User user) {
        return commentService.vote(user, this, false);
    }
    public Mono<Integer> cancelVote(@NonNull User user) {
        return commentService.cancelVote(user, this);
    }
    public Mono<Boolean> addNodeComment(@NonNull String userHexId, @NonNull @NotBlank String exhibitId, @NonNull @NotBlank String content) {
        if (getId()==null||type==null) return Mono.just(false);
        return commentService.save(new Comment(userHexId, type, exhibitId, content, this.getId().toHexString()));
    }
}