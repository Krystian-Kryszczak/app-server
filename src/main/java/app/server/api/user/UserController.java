package app.server.api.user;

import app.server.model.being.user.dto.UserDto;
import app.server.service.being.user.UserService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

@Controller("api/v1/users/")
public class UserController {
    @Inject
    private UserService userService;
    // -------------------------------------------------- //
    @Get("/{id}")
    public Publisher<UserDto> getUserInfo(String id) {
        return Mono.from(userService.findById(id)).mapNotNull(user -> userService.toUserDto(user).orElseThrow());
    }
}