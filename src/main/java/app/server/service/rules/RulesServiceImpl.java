package app.server.service.rules;

import app.server.model.rules.Rules;
import app.server.storage.repository.rules.group.GroupRulesRepository;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class RulesServiceImpl implements RulesService {
    final GroupRulesRepository groupRulesRepo;
    public RulesServiceImpl(GroupRulesRepository groupRulesRepo) {
        this.groupRulesRepo = groupRulesRepo;
    }
    @Override
    public Mono<String> getGroupRulesById(String hexId) {
        return Mono.from(groupRulesRepo.findById(hexId)).map(Rules::getContent);
    }
    @Override
    public Mono<String> getGroupRulesByTargetId(String targetHexId) {
        return groupRulesRepo.findByTargetId(targetHexId).map(Rules::getContent);
    }
    @Override
    public Mono<Boolean> setGroupRulesForTargetId(String targetHexId, String content) {
        return groupRulesRepo.findByTargetIdAndSetContent(targetHexId, content);
    }
    @Override
    public Mono<Boolean> deleteGroupRulesById(String hexId) {
        return Mono.from(groupRulesRepo.delete(hexId)).thenReturn(true).onErrorReturn(false);
    }
    @Override
    public Mono<Boolean> deleteGroupRulesByTargetId(String targetHexId) {
        return groupRulesRepo.deleteByTargetId(targetHexId).thenReturn(true).onErrorReturn(false);
    }
}