package app.server.storage.repository.rules;

import app.server.model.rules.Rules;
import app.server.storage.repository.Repository;
import reactor.core.publisher.Mono;

public interface RulesRepository<T extends Rules> extends Repository<T> {
    Mono<T> findByTargetId(String ownerHexId);
    Mono<T> deleteByTargetId(String ownerHexId);
    Mono<Boolean> findByTargetIdAndSetContent(String targetHexId, String content);
}