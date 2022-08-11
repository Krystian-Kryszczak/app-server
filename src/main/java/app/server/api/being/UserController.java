package app.server.api.being;

import app.server.service.being.user.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

@Secured(SecurityRule.IS_AUTHENTICATED) // TODO
@Controller("api/v1/users")
public class UserController { // TODO api/user/ -> feed, profile, friend, followed, info,
    // friends, video, shorts, photos, music, channels, information
    // group / user -> profile
    @Inject
    UserService userService;
    @Get("/search/{query}")
    public HttpResponse<String> search(String query) { // TODO
        return HttpResponse.ok();
    }
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{userId}/profile/")
    public HttpResponse<String> profile(String userId) {
        //
        return HttpResponse.ok(userId);
    }
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{userId}/profile/friends/")
    public HttpResponse<String> friends(String userId) { // TODO
        return HttpResponse.ok();
    }
    @Post("/friends/{friendHexId}")
    public Mono<HttpStatus> addToFriends(@PathVariable String friendHexId, Authentication authentication) {
        if (authentication.getAttributes().get("id") instanceof String) {
            ObjectId clientId = new ObjectId((String)authentication.getAttributes().get("id"));
            if (!ObjectId.isValid(friendHexId))
                return Mono.just(HttpStatus.NOT_ACCEPTABLE);
            ObjectId friendId = new ObjectId(friendHexId);
            return userService.addToFriend(clientId, friendId);
        }
        return Mono.just(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}