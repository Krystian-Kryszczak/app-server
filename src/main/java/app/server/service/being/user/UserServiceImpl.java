package app.server.service.being.user;

import app.server.model.being.user.User;
import app.server.model.being.user.UserDto;
import app.server.security.encoder.PasswordEncoder;
import app.server.storage.repository.being.user.UserRepository;
import app.server.storage.repository.history.user.UserHistoryRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

@Singleton
public class UserServiceImpl implements UserService {
    @Inject
    UserRepository userRepo;
    @Inject
    PasswordEncoder passwordEncoder;
    @Inject
    UserHistoryRepository userHistoryRepo;
    // ---------------------------------------------------------------------------------------------------- //
    public Mono<UserDto> createUser(String name, String lastname, String email, String password) {
        User user = User.builder().name(name).lastname(lastname).email(email).password(passwordEncoder.encode(password)).build();
        return Mono.from(userRepo.save(user)).mapNotNull(
            u -> new UserDto(Objects.requireNonNull(u.getInsertedId()).asObjectId().asObjectId().getValue().toHexString(), name, lastname));
    }
    public Publisher<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    public Publisher<User> findById(String hexId) {
        return userRepo.findById(hexId);
    }
    public Optional<UserDto> toUserDto(@NonNull User user) {
        return user.getId()!=null ? Optional.of(new UserDto(user.getId().toHexString(), user.getName(), user.getLastname())) : Optional.empty();
    }
    public Mono<HttpStatus> addToFriend(ObjectId clientId, ObjectId friendId) {
        return Mono.just(HttpStatus.ACCEPTED); // TODO
    }
}