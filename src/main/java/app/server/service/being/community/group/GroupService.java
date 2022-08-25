package app.server.service.being.community.group;

import app.server.model.being.community.group.Group;
import app.server.service.being.community.CommunityService;
import reactor.core.publisher.Mono;

public interface GroupService extends CommunityService<Group> {
    Mono<String> groupsRecommendation(String clientHexId);
}