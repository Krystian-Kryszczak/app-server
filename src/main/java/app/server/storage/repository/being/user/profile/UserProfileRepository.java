package app.server.storage.repository.being.user.profile;

import app.server.model.being.user.profile.UserProfile;
import app.server.model.exhibit.ExhibitType;
import app.server.storage.repository.Repository;
import org.bson.Document;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserProfileRepository extends Repository<UserProfile> {
    Mono<UserProfile> findByUserId(String userHexId);
    Mono<Document> findDocByUserId(String userHexId);
    Flux<String> getFollowedForUserByHexId(String userHexId, int limit, int skip);
    Mono<Boolean> addToFollowedForUserByHexId(String userHexId, String followUserHexId);
    Mono<Boolean> removeToFollowedForUserByHexId(String userHexId, String followUserHexId);
    Flux<String> getExhibitsProfile(String userHexId, int limit, int skip);
    Mono<Boolean> shareOnProfile(String userHexId, ExhibitType type, String exhibitHexId);
    Mono<Boolean> addToProfile(String userHexId, ExhibitType type, String exhibitHexId);
    Mono<Boolean> deleteFromProfile(String userHexId, String exhibitHexId);
}