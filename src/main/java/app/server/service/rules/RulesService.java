package app.server.service.rules;

import reactor.core.publisher.Mono;

public interface RulesService {
    Mono<String> getGroupRulesById(String hexId);
    Mono<String> getGroupRulesByTargetId(String targetHexId);
    Mono<Boolean> setGroupRulesForTargetId(String targetHexId, String content);
    Mono<Boolean> deleteGroupRulesById(String hexId);
    Mono<Boolean> deleteGroupRulesByTargetId(String targetHexId);
}