package app.server.service.being.community.group;

import app.server.model.being.community.group.Group;
import app.server.service.being.community.CommunityServiceImpl;
import app.server.storage.repository.being.community.group.GroupRepository;
import com.google.gson.Gson;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Singleton
public class GroupServiceImpl extends CommunityServiceImpl<Group> implements GroupService {
    final GroupRepository groupRepository; Gson gson = new Gson();
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    @Override
    public Mono<String> groupsRecommendation(String clientHexId) {
        return getRepo().getGroupsRecommendation(clientHexId).collect(Collectors.toList()).map(gson::toJson);
    }
    protected GroupRepository getRepo() {
        return groupRepository;
    }
}