package app.server.service.being.user;

import app.server.model.being.user.User;
import app.server.model.being.user.UserDto;
import app.server.service.being.BeingService;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserService extends BeingService<User> {
    Mono<UserDto> createUser(String name, String lastname, String email, String password);
    Publisher<User> findByEmail(String email);
    Publisher<User> findById(String hexId);
    Optional<UserDto> toUserDto(User user);
}