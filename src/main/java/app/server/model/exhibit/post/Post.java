package app.server.model.exhibit.post;

import app.server.model.being.user.User;
import app.server.model.exhibit.Exhibit;
import app.server.service.exhibit.post.PostService;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.reactivestreams.Publisher;

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
    public Post(@NonNull @BsonId String hexId, @NonNull @BsonProperty("rating") int rating, @NonNull @BsonProperty("caption") String caption, @NonNull @BsonProperty("datetime") LocalDateTime dateTime, @NonNull @BsonProperty("media") List<String> media) {
        super(hexId, rating, dateTime);
        this.caption = caption;
        this.media = media;
    }
    public Post(@NonNull @BsonProperty("caption") String caption, @NonNull @BsonProperty("media") List<String> media) {
        super(); // hexId = null & rating = null
        this.caption = caption;
        this.media = media;
    }
    // ---------------------------------------------------------------------------------------------------- //
//    @Override
//    public Mono<Boolean> voteUp(@NonNull User user) { // OK
//        return Mono.from(postService.getVote(user, this)).map(vote -> {
//            if (vote!=null && vote) return false; // check if user not voted up (true - voted up, false - voted down, null - notVoted) to this media else return false because value not will change
//            // --------------------------------------------------------------------------- //
//            if (vote==null) { // check if user not voted (down) to this media
//                localRateUp(); //rating++;
//                postService.voteUp(user, getHexId());
//            } else /*!vote*/ {
//                localRateUp(2); // rating+=2;
//                postService.voteUp(user, getHexId()); // rateUp
//            }
//            return true;
//        });
//    }
//    @Override
//    public Future<Boolean> voteDown(@NonNull User user) { // OK
//        return Maybe.fromFuture(userService.getVotePost(user, this)).map(vote -> {
//            if (vote!=null && !vote) return false; // check if user not voted down (true - voted up, false - voted down, null - notVoted) to this media else return false because value not will change
//            // --------------------------------------------------------------------------- //
//            if (vote==null) { // check if user not voted (up) to this media
//                localRateDown(); //rating--;
//                postService.voteDown(user);
//            } else /*!vote*/ {
//                localRateDown(2); //rating-=2;
//                postService.voteDown(user); // rateDown
//            }
//            return true;
//        }).toFuture();
//    }
    @Override
    public String getUrl() {
        return abstractUrl("posts");
    }
    @Override
    public Publisher<Boolean> report(@NonNull User user, @NonNull String content) {
        return getReport().reportPost(getHexId(), user, content);
    }
    @Override
    public Publisher<Boolean> reportToAdmin(@NonNull User user, @NonNull String content) {
        return getReport().reportPostToAdmin(getHexId(), user, content);
    }
}