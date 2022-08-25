package app.server.storage.repository.exhibit;

import app.server.model.exhibit.Exhibit;
import app.server.storage.repository.votable.VotableRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExhibitRepository<T extends Exhibit<T>> extends VotableRepository<T> {
    Flux<T> getFeedForUser(String userHexId, int limit, int skip);
    Mono<Boolean> uploadByUser(T exhibit, String clientHexId);
}