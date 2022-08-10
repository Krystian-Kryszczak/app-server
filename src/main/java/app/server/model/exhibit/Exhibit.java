package app.server.model.exhibit;

import app.server.model.being.user.User;
import app.server.model.comment.Comment;
import app.server.model.comment.SortBy;
import app.server.service.report.ReportService;
import com.google.gson.Gson;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Inject;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Introspected
public abstract class Exhibit<T extends Exhibit<T>> { // TODO // TODO delete & hide exhibit
    @Inject
    static ReportService reportService;
    // --------------------------------------------------------------------------- //
    @Nullable
    @BsonId
    final ObjectId id;
    @Nullable
    @BsonProperty("rating")
    Integer rating; // voting
    @NonNull
    @BsonProperty("datetime")
    final LocalDateTime dateTime;
    // ---------------------------------------------------------------------------------------------------- //
    @Creator
    @BsonCreator
    public Exhibit(@NonNull @BsonId ObjectId id, @BsonProperty("rating") int rating, @NonNull @BsonProperty("datetime") LocalDateTime dateTime) {
        this.id = id;
        this.rating = rating;
        this.dateTime = dateTime;
    }
    public Exhibit() {
        this.id = null;
        this.rating = null;
        this.dateTime = LocalDateTime.now();
    }
    // ---------------------------------------------------------------------------------------------------- //
    // #Rating
    public int getRating() {
        return rating;
    }
    public Publisher<Integer> voteUp(@NonNull User user) {
        return null;
        // TODO local variable update (optional)
    }
    public Publisher<Integer> voteDown(@NonNull User user) {
        return null;
        // TODO local variable update (optional)
    }
    public Publisher<Integer> cancelVote() {
        // TODO local variable update (optional)
        return null;
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
    // --------------------------------------------------------------------------- // // TODO
    public Flux<List<Comment>> getComments(@NonNull User user, @Nullable SortBy sortBy) {
        return null; // TODO
    }
    public Flux<String> addComment(@NonNull User user, @NonNull @NotBlank String content) {
        return null; // TODO
    }
    public Flux<String> editComment(@NonNull User user, @NonNull @NotBlank String hexId, @NonNull @NotBlank String newContent) {
        return null; // TODO
    }
    public Flux<Boolean> removeComment(@NonNull User user, @NonNull @NotBlank String hexId) {
        return null; // TODO
    }
    public Flux<Boolean> hideComment(@NonNull User user, @NonNull @NotBlank String hexId) {
        return null; // TODO
    }
    // --------------------------------------------------------------------------- //
    public Flux<Boolean> shareOnProfile(@NonNull User user) {
        return null; // TODO
    }
    // --------------------------------------------------------------------------- //
    public String getUrl() {
        ObjectId id = getId();
        return id!=null ? "/"+this.getClass().getSimpleName().toLowerCase()+"/"+ getId().toHexString()+"/" : "";
    }
    protected String abstractUrl(String prefix) {
        ObjectId id = getId();
        return id!=null ? "/"+prefix+"/"+ getId().toHexString()+"/" : "";
    }
    // --------------------------------------------------------------------------- //
    protected ReportService getReport() {
        return reportService;
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
    public Flux<String> getHistory(@NonNull User user) {
        // TODO check permissions
        String SimpleName = getClass().getSimpleName();
        return null; // TODO
    }
}