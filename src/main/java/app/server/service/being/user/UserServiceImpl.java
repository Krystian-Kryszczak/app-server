package app.server.service.being.user;

import app.server.model.being.user.User;
import app.server.model.being.user.UserDto;
import app.server.security.PasswordEncoder;
import app.server.storage.repository.being.user.UserRepository;
import app.server.storage.repository.history.user.UserHistoryRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

@Singleton
public class UserServiceImpl implements UserService {
    @Inject
    UserRepository userRepo;
    @Inject
    UserHistoryRepository userHistoryRepo;
    // ---------------------------------------------------------------------------------------------------- //
    public Mono<UserDto> createUser(String name, String lastname, String email, String password) {
        User user = User.builder().name(name).lastname(lastname).email(email).password(PasswordEncoder.sha256(password)).build();
        return Mono.from(userRepo.save(user)).mapNotNull(
                u -> new UserDto(Objects.requireNonNull(u.getInsertedId()).asObjectId().asObjectId().getValue().toHexString(), name, lastname));
    }
    public Publisher<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    public Publisher<User> findById(String hexId) {
        return userRepo.findById(hexId);
    }
    public Optional<UserDto> toUserDto(User user) {
        return user.getId()!=null ? Optional.of(new UserDto(user.getId().toHexString(), user.getName(), user.getLastname())) : Optional.empty();
    }
}