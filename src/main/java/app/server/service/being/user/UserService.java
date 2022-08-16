package app.server.service.being.user;

import app.server.model.being.user.User;
import app.server.model.being.user.dto.UserDto;
import app.server.model.being.user.profile.UserProfile;
import app.server.service.being.BeingService;
import io.micronaut.http.HttpStatus;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface UserService extends BeingService<User> {
    Mono<UserDto> createUser(String name, String lastname, String email, String password);
    Publisher<User> findByEmail(String email);
    Publisher<User> findById(String hexId);
    Optional<UserDto> toUserDto(User user);
    Mono<UserProfile> getUserProfile(ObjectId userId);
    Flux<String> getUserFriends(String userHexId);
    Mono<HttpStatus> addToFriends(ObjectId clientId, ObjectId friendId);
    Mono<HttpStatus> removeFromFriends(ObjectId clientId, ObjectId friendId);
    Mono<List<String>> proposedFriendsForUser(ObjectId clientId);
    Flux<String> searchUsers(String query, ObjectId userId);
    Flux<String> searchUsers(String query);
    Mono<Boolean> shareOnProfile(String userHexId, String exhibitHexId, String exhibitClassName);
    // Media //
    Flux<String> video(String userHexId);
    Flux<String> shorts(String userHexId);
    Flux<String> images(String userHexId);
    Flux<String> songs(String userHexId);
    Flux<String> followed(String userHexId);
}