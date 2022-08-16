package app.server.model.exhibit.post;

import app.server.model.being.user.User;
import app.server.model.exhibit.Exhibit;
import app.server.service.exhibit.ExhibitService;
import app.server.service.exhibit.post.PostService;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Introspected
public class Post extends Exhibit<Post> {
    @Inject
    static PostService postService;
    // ---------------------------------------------------------------------------------------------------- //
    @NonNull
    @BsonProperty("caption")
    final String caption;
    @NonNull
    @BsonProperty("media")
    final List<String> media; // UrlList
    // ---------------------------------------------------------------------------------------------------- //
    @Creator
    @BsonCreator
    public Post(@NonNull @BsonId ObjectId hexId, @BsonProperty("rating") int rating, @NonNull @BsonProperty("caption") String caption, @NonNull @BsonProperty("datetime") LocalDateTime dateTime, @NonNull @BsonProperty("media") List<String> media) {
        super(hexId, rating, dateTime);
        this.caption = caption;
        this.media = media;
    }
    public Post(@NonNull @BsonProperty("caption") String caption, @NonNull @BsonProperty("media") List<String> media) {
        super(); // hexId = null & rating = null
        this.caption = caption;
        this.media = media;
    }
    @Override
    public String getUrl() {
        return abstractUrl("posts");
    }
    @Override
    public Publisher<Boolean> report(@NonNull User user, @NonNull String content) {
        return getReport().reportPost(getId(), user, content);
    }
    @Override
    public Publisher<Boolean> reportToAdmin(@NonNull User user, @NonNull String content) {
        return getReport().reportPostToAdmin(getId(), user, content);
    }
    @Override
    public Flux<String> getHistory(@NonNull String userHexId) {
        return getExhibitHistoryService().getPostHistory(getId().toHexString(), userHexId);
    }

    @Override
    protected ExhibitService<Post> getExhibitService() {
        return postService;
    }
}