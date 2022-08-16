package app.server.api.being.user;

import app.server.api.being.BeingController;
import app.server.model.being.user.User;
import app.server.model.being.user.profile.UserProfile;
import app.server.service.being.user.UserService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("api/v1/users")
public class UserController extends BeingController<User> {
    final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
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
        return Mono.from(userService.findById(userId))
            .mapNotNull(user -> {
                Map<String, String> map = new HashMap<>();
                ObjectId userObjId = user.getId();
                if (userObjId!=null)
                    map.put("id", user.getId().toHexString());
                map.put("email", user.getEmail());
                map.put("name", user.getName());
                map.put("lastname", user.getLastname());
                return map;
            });
    }
    // Media //
        // Video //
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{userHexId}/video/")
    public Flux<String> video(String userHexId) {
        return userService.video(userHexId);
    }
        // Shorts //
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{userHexId}/shorts/")
    public Flux<String> shorts(String userHexId) {
        return userService.shorts(userHexId);
    }
        // Images //
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{userHexId}/images/")
    public Flux<String> images(String userHexId) {
        return userService.images(userHexId);
    }
        // Music //
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{userHexId}/songs/")
    public Flux<String> music(String userHexId) {
        return userService.songs(userHexId);
    }
    // Followed //
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{userHexId}/followed")
    public Flux<String> followed(String userHexId) {
        return userService.followed(userHexId);
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
        return userService.getUserFriends(userId).collect(Collectors.toList()).map(HttpResponse::ok);
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