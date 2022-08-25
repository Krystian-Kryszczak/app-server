package app.server.model.exhibit;

import app.server.model.StorageItem;
import app.server.model.being.user.User;
import app.server.model.comment.Comment;
import app.server.model.comment.sorting.SortBy;
import app.server.service.being.user.UserService;
import app.server.service.exhibit.ExhibitService;
import app.server.service.history.exhibit.ExhibitHistoryService;
import app.server.service.report.ReportService;
import com.google.gson.Gson;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Inject;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public abstract class Exhibit<T extends Exhibit<T>> implements StorageItem {
    @Inject
    static ExhibitHistoryService exhibitHistoryService;
    @Inject
    static UserService userService;
    @Inject
    static ReportService reportService;
    // --------------------------------------------------------------------------- //
    @Nullable
    @BsonId
    final ObjectId id;
    @NonNull @Getter
    @BsonProperty("userHexId")
    final String userHexId;
    @Nullable
    @BsonProperty("rating")
    Integer rating; // voting
    @NonNull
    @BsonProperty("datetime")
    final LocalDateTime dateTime;
    // ---------------------------------------------------------------------------------------------------- //
    @Creator
    @BsonCreator
    public Exhibit(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("userHexId") String userHexId, @BsonProperty("rating") int rating, @NonNull @BsonProperty("datetime") LocalDateTime dateTime) {
        this.id = id;
        this.userHexId = userHexId;
        this.rating = rating;
        this.dateTime = dateTime;
    }
    public Exhibit(@NonNull String userHexId) {
        this.id = null;
        this.userHexId = userHexId;
        this.rating = null;
        this.dateTime = LocalDateTime.now();
    }
    // ---------------------------------------------------------------------------------------------------- //
    // #Rating
    public int getRating() {
        return rating;
    }
    public Publisher<Integer> voteUp(@NonNull User user) {
        return voteUp(user.getId());
    }
    public Publisher<Integer> voteDown(@NonNull User user) {
        return voteDown(user.getId());
    }
    public Publisher<Integer> voteUp(ObjectId userObjectId) {
        if (userObjectId==null) return Mono.just(getRating());
        return voteUp(userObjectId.toHexString());
    }
    public Publisher<Integer> voteDown(ObjectId userObjectId) {
        if (userObjectId==null) return Mono.just(getRating());
        return voteDown(userObjectId.toHexString());
    }
    protected Publisher<Integer> voteUp(@NonNull String userHexId) {
        return getExhibitService().vote(userHexId, getId().toHexString(), true);
    }
    protected Publisher<Integer> voteDown(@NonNull String userHexId) {
        return getExhibitService().vote(userHexId, getId().toHexString(), false);
    }
    protected Publisher<Integer> cancelVote(@NonNull String userHexId) {
        return Mono.from(getExhibitService().cancelVote(userHexId, getId().toHexString()));
    }
    protected void setRating(int value) { // LocalVariable
        if (rating!=null)
            rating = value;
    }
    protected void localRateUp() { // LocalVariable
        if (rating!=null)
            rating++;
    }
    protected void localRateDown() { // LocalVariable
        if (rating!=null)
            rating--;
    }
    protected void localRateUp(int value) { // LocalVariable
        if (rating!=null)
            rating+=value;
    }
    protected void localRateDown(int value) { // LocalVariable
        if (rating!=null)
            rating-=value;
    }
    protected Integer getLocalRating() { // LocalVariable
        return rating;
    }
    // --------------------------------------------------------------------------- //
    public Flux<Comment> getComments(@NonNull String userHexId, @Nullable SortBy sortBy) {
        return getExhibitService().getComments(userHexId, getId().toHexString(), sortBy);
    }
    public Mono<Boolean> addComment(@NonNull String userHexId, @NonNull @NotBlank String content) {
        return getExhibitService().addComment(userHexId, getType(), getId().toHexString(), content);
    }
    public Mono<Boolean> addNodeComment(@NonNull String userHexId, @NonNull String commentHexId, @NonNull @NotBlank String content) {
        return getExhibitService().addNodeComment(userHexId, commentHexId, content);
    }
    public Mono<String> editComment(@NonNull String userHexId, @NonNull @NotBlank String commentHexId, @NonNull @NotBlank String newContent) {
        return getExhibitService().editComment(userHexId, commentHexId, newContent);
    }
    public Mono<Boolean> deleteComment(@NonNull String userHexId, @NonNull @NotBlank String commentHexId) {
        return getExhibitService().deleteComment(userHexId, commentHexId);
    }
    public Mono<Boolean> hideComment(@NonNull String userHexId, @NonNull @NotBlank String commentHexId) {
        return getExhibitService().hideComment(userHexId, commentHexId);
    }
    // --------------------------------------------------------------------------- //
    public Mono<Boolean> shareOnProfile(@NonNull String userHexId) {
        return userService.shareOnProfile(userHexId, getType(), getId().toHexString());
    }
    // --------------------------------------------------------------------------- //
    public String getUrl() {
        return getId()!=null ? "/"+this.getType().getUrlModifier()+"/"+ getId().toHexString()+"/" : "";
    }
    // --------------------------------------------------------------------------- //
    @SuppressWarnings("all")
    public ObjectId getId() throws NullPointerException {
        if (getId()==null) throw new NullPointerException(getClass().getSimpleName()+" hexId equals null.");
        return id;
    }
    // #Reporting
    public abstract Publisher<Boolean> report(@NonNull User user, @NonNull @NotBlank String content);
    public abstract Publisher<Boolean> reportToAdmin(@NonNull User user, @NonNull @NotBlank String content);
    // #JSON
    String toJson() {
        return new Gson().toJson(this);
    }
    // #History
    public abstract Flux<String> getHistory(@NonNull String userHexId);
    public ExhibitHistoryService getExhibitHistoryService() {
        return exhibitHistoryService;
    }
    // --------------------------------------------------------------------------- //
    protected ReportService getReport() {
        return reportService;
    }
    protected abstract ExhibitService<T> getExhibitService();
    protected abstract ExhibitType getType();
    // --------------------------------------------------------------------------- //
    private String getClassName() {
        return this.getClass().getSimpleName();
    }
}