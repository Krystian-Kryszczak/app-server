package app.server.service.being.user;

import app.server.model.being.user.User;
import app.server.model.being.user.UserDto;
import app.server.service.being.BeingService;
import app.server.storage.repository.being.user.UserRepository;
import app.server.storage.repository.history.user.UserHistoryRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;

import java.util.Optional;

@Singleton
public class UserService extends BeingService<User> {
    @Inject
    UserRepository userRepo;
    @Inject
    UserHistoryRepository userHistoryRepo;
    // ---------------------------------------------------------------------------------------------------- //
    public UserDto createUser(String name, String lastname, String email, String password) { // TOOD
        //User user = User.builder().name(name).lastname(lastname).email(email).password(PasswordEncoder.sha256(password)).build();
        //return new UserDto(Maybe.fromPublisher(userRepo.save(user)).blockingGet().getInsertedId().asObjectId().asObjectId().getValue().toHexString(), name, lastname);
        return null;
    }
    public Publisher<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    public Optional<UserDto> toUserDto(User user) {
        //return user.getHexId()!=null ? Optional.of(new UserDto(user.getHexId(), user.getName(), user.getLastname())) : Optional.empty();
        return null;
    }
}