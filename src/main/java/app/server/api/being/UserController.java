package app.server.api.being;

import app.server.model.being.user.profile.UserProfile;
import app.server.service.being.user.UserService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("api/v1/users")
public class UserController {
    @Inject
    UserService userService;
    // Search //
    @Get("/search/{query}")
    public Flux<String> search(@NonNull @NotBlank String query, Authentication authentication) {
        if (authentication!=null && authentication.getAttributes().get("id") instanceof String) {
            String hexId = (String) authentication.getAttributes().get("id");
            if (!hexId.isBlank()) {
                ObjectId clientId = new ObjectId(hexId);
                return userService.searchUsers(query, clientId);
            }
        }
        return userService.searchUsers(query);
    }
    // Profile //
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{userId}/profile/")
    public Mono<? extends HttpResponse<UserProfile>> profile(String userId) {
        if (!ObjectId.isValid(userId)) return Mono.just(HttpResponse.status(HttpStatus.NOT_ACCEPTABLE));
        return userService.getUserProfile(new ObjectId(userId))
            .map(HttpResponse::ok)
                .switchIfEmpty(Mono.just(HttpResponse.status(HttpStatus.NOT_FOUND)))
                    .onErrorReturn(HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR));
    }
    // Information //
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{userId}/info")
    public Mono<Map<String, String>> info(String userId) {
        return Mono.empty(); // TODO
    }
    // Media //
        // Video //
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{userId}/video/")
    public Mono<Map<String, String>> video(String userId) {
        return Mono.empty(); // TODO
    }
        // Shorts //
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{userId}/shorts/")
    public Mono<Map<String, String>> shorts(String userId) {
        return Mono.empty(); // TODO
    }
        // Images //
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{userId}/images/")
    public Mono<Map<String, String>> images(String userId) {
        return Mono.empty(); // TODO
    }
        // Music //
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{userId}/music/")
    public Mono<Map<String, String>> music(String userId) {
        return Mono.empty(); // TODO
    }
    // Followed //
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{userId}/followed")
    public Mono<Map<String, String>> followed(String userId) {
        return Mono.empty(); // TODO
    }
    // Friends //
    @Get("/friends/")
    public Mono<HttpResponse<List<String>>> feed(Authentication authentication) {
        if (authentication.getAttributes().get("id") instanceof String) {
            ObjectId clientId = new ObjectId((String)authentication.getAttributes().get("id"));
            return userService.proposedFriendsForUser(clientId).map(HttpResponse::ok);
        }
        return Mono.just(HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR));
    }
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{userId}/friends/")
    public Mono<HttpResponse<List<String>>> friends(String userId) {
        if (!ObjectId.isValid(userId))
            return Mono.just(HttpResponse.status(HttpStatus.NOT_ACCEPTABLE));
        ObjectId id = new ObjectId(userId);
        return userService.getUserFriends(id).map(HttpResponse::ok);
    }
    @Post("/friends/{friendHexId}")
    public Mono<HttpStatus> addToFriends(@PathVariable String friendHexId, Authentication authentication) {
        if (authentication.getAttributes().get("id") instanceof String) {
            ObjectId clientId = new ObjectId((String)authentication.getAttributes().get("id"));
            if (!ObjectId.isValid(friendHexId))
                return Mono.just(HttpStatus.NOT_ACCEPTABLE);
            ObjectId friendId = new ObjectId(friendHexId);
            return userService.addToFriends(clientId, friendId);
        }
        return Mono.just(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Delete("/friends/{friendHexId}")
    public Mono<HttpStatus> removeFromFriends(@PathVariable String friendHexId, Authentication authentication) {
        if (authentication.getAttributes().get("id") instanceof String) {
            ObjectId clientId = new ObjectId((String)authentication.getAttributes().get("id"));
            if (!ObjectId.isValid(friendHexId))
                return Mono.just(HttpStatus.NOT_ACCEPTABLE);
            ObjectId friendId = new ObjectId(friendHexId);
            return userService.removeFromFriends(clientId, friendId);
        }
        return Mono.just(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}