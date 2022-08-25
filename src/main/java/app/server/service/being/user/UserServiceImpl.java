package app.server.service.being.user;

import app.server.model.being.user.User;
import app.server.model.being.user.dto.UserDto;
import app.server.model.being.user.friends.UserFriends;
import app.server.model.being.user.profile.UserProfile;
import app.server.model.exhibit.ExhibitType;
import app.server.security.encoder.PasswordEncoder;
import app.server.service.ServiceConfiguration;
import app.server.service.being.BeingServiceImpl;
import app.server.service.exhibit.music.SongService;
import app.server.service.exhibit.post.PostService;
import app.server.service.exhibit.reel.ReelService;
import app.server.service.exhibit.watch.WatchService;
import app.server.storage.repository.being.BeingRepository;
import app.server.storage.repository.being.user.UserRepository;
import app.server.storage.repository.being.user.friends.UserFriendsRepository;
import app.server.storage.repository.being.user.profile.UserProfileRepository;
import app.server.storage.repository.history.being.user.UserHistoryRepository;
import com.google.gson.Gson;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class UserServiceImpl extends BeingServiceImpl<User> implements UserService {
    @Inject
    static ServiceConfiguration serviceConfig;
    static final int _limit = serviceConfig.getDefaultFeedLimit();
    @Inject
    UserRepository userRepo;
    @Inject
    UserProfileRepository userProfileRepo;
    @Inject
    UserFriendsRepository userFriendsRepo;
    @Inject
    PasswordEncoder passwordEncoder;
    @Inject
    UserHistoryRepository userHistoryRepo;
    // -------------------------------------------------- //
    @Inject
    WatchService watchService;
    @Inject
    ReelService reelService;
    @Inject
    PostService postService;
    @Inject
    SongService songService;
    Gson gson = new Gson();
    // ---------------------------------------------------------------------------------------------------- //
    public Mono<UserDto> createUser(String name, String lastname, String email, String password) {
        User user = new User(name, lastname, email, passwordEncoder.encode(password));
        return Mono.from(userRepo.save(user)).mapNotNull(
            _user -> new UserDto(Objects.requireNonNull(_user.getInsertedId()).asObjectId().asObjectId().getValue().toHexString(), name, lastname));
    }
    public Publisher<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    public Mono<User> findById(String hexId) {
        return Mono.from(userRepo.findById(hexId));
    }
    protected BeingRepository<User> getRepo() {
        return userRepo;
    }
    // ---------------------------------------------------------------------------------------------------- //
    public Optional<UserDto> toUserDto(@NonNull User user) {
        return user.getId()!=null ? Optional.of(new UserDto(user.getId().toHexString(), user.getName(), user.getLastname())) : Optional.empty();
    }
    public Mono<UserProfile> getUserProfile(@NonNull ObjectId userId) {
        return getUserProfile(userId.toHexString());
    }
    public Mono<UserProfile> getUserProfile(@NonNull String userHexId) {
        return userProfileRepo.findByUserId(userHexId);
    }
    public Mono<Document> getDocUserProfile(@NonNull String userHexId) {
        return userProfileRepo.findDocByUserId(userHexId);
    }
    public Mono<HttpStatus> addToFriends(String userHexId, String friendHexId) {
        return userFriendsRepo.addUserToFriends(userHexId, friendHexId)
            .map(result -> result ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT)
                .onErrorReturn(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public Mono<HttpStatus> removeFromFriends(String userHexId, String friendHexId) {
        return userFriendsRepo.removeUserFromFriends(userHexId, friendHexId)
            .map(result -> result ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND)
                .onErrorReturn(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public Mono<List<String>> proposedFriendsForUser(String userHexId) {
        Mono<? extends List<String>> userFriends = userFriendsRepo.findByUserHexId(userHexId)
            .map(UserFriends::getFriends).map(ArrayList::new).defaultIfEmpty(new ArrayList<>());
        return userFriends.flatMap(uf -> !uf.isEmpty()?userFriends.flatMap(friends -> userFriendsRepo.findFriendsByUserId(friends.get(new Random().nextInt(friends.size())))
            .collect(Collectors.toList()).map(list -> {list.removeAll(friends); return list;})):userFriends);
    }
    public Flux<String> searchUsers(String query, String userHexId) {
        return userRepo.search(query, userHexId);
    }
    public Mono<Boolean> shareOnProfile(String userHexId, ExhibitType type, String exhibitHexId) {
        return userProfileRepo.shareOnProfile(userHexId, type, exhibitHexId);
    }
    public Mono<Boolean> deleteFromProfile(String userHexId, String exhibitHexId) {
        return userProfileRepo.deleteFromProfile(userHexId, exhibitHexId);
    }
    // Media //
    public Flux<String> watch(String userHexId) {
        return watch(userHexId, _limit, 0);
    }
    public Flux<String> watch(String userHexId, int limit, int skip) {
        return watchService.getFeedForUser(userHexId, limit, skip).map(gson::toJson);
    }
    public Flux<String> shorts(String userHexId) {
        return shorts(userHexId, _limit, 0);
    }
    public Flux<String> shorts(String userHexId, int limit, int skip) {
        return reelService.getFeedForUser(userHexId, limit, skip).map(gson::toJson);
    }
    public Flux<String> posts(String userHexId) {
        return posts(userHexId, _limit, 0);
    }
    public Flux<String> posts(String userHexId, int limit, int skip) {
        return postService.getFeedForUser(userHexId, limit, skip).map(gson::toJson);
    }
    public Flux<String> songs(String userHexId) {
        return songs(userHexId, _limit, 0);
    }
    public Flux<String> songs(String userHexId, int limit, int skip) {
        return songService.getFeedForUser(userHexId, limit, skip).map(gson::toJson);
    }
    public Flux<String> followed(String userHexId) {
        return followed(userHexId, _limit, 0);
    }
    public Flux<String> followed(String userHexId, int limit, int skip) {
        return userProfileRepo.getFollowedForUserByHexId(userHexId, limit, skip);
    }
    // Friends //
    public Flux<String> getUserFriendsHexIds(String userHexId) {
        return userFriendsRepo.findFriendsByUserId(userHexId);
    }
    // Groups //
    public Flux<String> getUserGroupsIds(String userHexId) {
        return Mono.from(userRepo.findDocumentById(userHexId)).map(doc -> doc.getList("groups", String.class)).flatMapMany(Flux::fromIterable);
    }
}