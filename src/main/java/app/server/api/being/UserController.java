package app.server.api.being;

import app.server.service.being.user.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;

@Secured(SecurityRule.IS_ANONYMOUS) // TODO
@Controller("api/v1/users")
public class UserController { // TODO api/user/ -> feed, profile, friend, followed, info,
    @Inject
    UserService userService;
    @Get("/search/{query}")
    public HttpResponse<String> search(String query) { // TODO
        return HttpResponse.ok();
    }
    @Get("/{userId}/profile/")
    public HttpResponse<String> profile(String userId) {
        //
        return HttpResponse.ok(userId);
    }
    @Get("/{userId}/profile/friends/")
    public HttpResponse<String> friends(String userId) { // TODO
        return HttpResponse.ok();
    }
    @Post("/friends/")
    public HttpResponse<String> addToFriends() {
        return HttpResponse.ok();
    }
}
// friends, video, shorts, photos, music, channels, information
// group / user -> profile