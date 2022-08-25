package app.server.storage.repository.being.community.group;

import app.server.model.being.community.group.Group;
import app.server.storage.repository.being.community.CommunityRepository;
import org.bson.Document;
import reactor.core.publisher.Flux;

public interface GroupRepository extends CommunityRepository<Group> {
    Flux<Document> getGroupsRecommendation(String clientHexId);
}