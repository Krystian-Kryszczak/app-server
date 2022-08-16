package app.server.service.being.user;

import app.server.model.being.user.User;
import app.server.model.being.user.dto.UserDto;
import app.server.model.being.user.profile.UserProfile;
import app.server.security.encoder.PasswordEncoder;
import app.server.storage.repository.being.user.UserRepository;
import app.server.storage.repository.being.user.friends.UserFriendsRepository;
import app.server.storage.repository.history.being.user.UserBeingHistoryRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Singleton
public class UserServiceImpl implements UserService {
    @Inject
    UserRepository userRepo;
    @Inject
    UserFriendsRepository userFriendsRepository;
    @Inject
    PasswordEncoder passwordEncoder;
    @Inject
    UserBeingHistoryRepository userHistoryRepo;
    // ---------------------------------------------------------------------------------------------------- //
    public Mono<UserDto> createUser(String name, String lastname, String email, String password) {
        User user = User.builder().name(name).lastname(lastname).email(email).password(passwordEncoder.encode(password)).build();
        return Mono.from(userRepo.save(user)).mapNotNull(
            _user -> new UserDto(Objects.requireNonNull(_user.getInsertedId()).asObjectId().asObjectId().getValue().toHexString(), name, lastname));
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
    public Mono<UserProfile> getUserProfile(ObjectId userId) {
        return Mono.empty(); // TODO
    }
    public Mono<HttpStatus> addToFriends(ObjectId clientId, ObjectId friendId) {
        return userFriendsRepository.addUserToFriends(clientId, friendId)
            .map(result -> result ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT)
                .onErrorReturn(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public Mono<HttpStatus> removeFromFriends(ObjectId clientId, ObjectId friendId) {
        return userFriendsRepository.removeUserFromFriends(clientId, friendId)
            .map(result -> result ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND)
                .onErrorReturn(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public Mono<List<String>> proposedFriendsForUser(ObjectId clientId) {
        return Mono.just(List.of()); // TODO
    }
    public Flux<String> searchUsers(String query, ObjectId userId) {
        return Flux.empty(); // TODO
    }
    public Flux<String> searchUsers(String query) {
        return Flux.empty(); // TODO
    }
    @Override
    public Mono<Boolean> shareOnProfile(String userHexId, String exhibitHexId, String exhibitClassName) {
        return null;
    }
    // Media //
    public Flux<String> video(String userHexId) {
        return Flux.empty(); // TODO
    }
    public Flux<String> shorts(String userHexId) {
        return Flux.empty(); // TODO
    }
    public Flux<String> images(String userHexId) {
        return Flux.empty(); // TODO
    }
    public Flux<String> songs(String userHexId) {
        return Flux.empty(); // TODO
    }
    public Flux<String> followed(String userHexId) {
        return Flux.empty(); // TODO
    }
    // Friends //
    public Flux<String> getUserFriends(String userHexId) {
        return userFriendsRepository.findFriendsByUserId(userHexId);
    }
}