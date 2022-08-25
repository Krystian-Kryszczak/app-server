package app.server.service.being.user;

import app.server.model.being.user.User;
import app.server.model.being.user.dto.UserDto;
import app.server.model.being.user.profile.UserProfile;
import app.server.model.exhibit.ExhibitType;
import app.server.service.being.BeingService;
import io.micronaut.http.HttpStatus;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface UserService extends BeingService<User> {
    Mono<UserDto> createUser(String name, String lastname, String email, String password);
    Publisher<User> findByEmail(String email);
    Mono<User> findById(String hexId);
    Optional<UserDto> toUserDto(User user);
    Mono<UserProfile> getUserProfile(ObjectId userId);
    Mono<UserProfile> getUserProfile(String userHexId);
    Mono<Document> getDocUserProfile(String userHexId);
    Flux<String> getUserFriendsHexIds(String userHexId);
    Flux<String> getUserGroupsIds(String userHexId);
    Mono<HttpStatus> addToFriends(String userHexId, String friendHexId);
    Mono<HttpStatus> removeFromFriends(String userHexId, String friendHexId);
    Mono<List<String>> proposedFriendsForUser(String userHexId);
    Flux<String> searchUsers(String query, String userHexId);
    Mono<Boolean> shareOnProfile(String userHexId, ExhibitType type, String exhibitHexId);
    // Media //
    Flux<String> watch(String userHexId);
    Flux<String> shorts(String userHexId);
    Flux<String> posts(String userHexId);
    Flux<String> songs(String userHexId);
    Flux<String> followed(String userHexId);
}