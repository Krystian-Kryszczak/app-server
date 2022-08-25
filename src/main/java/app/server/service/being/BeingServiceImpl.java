package app.server.service.being;

import app.server.model.being.Being;
import app.server.storage.repository.being.BeingRepository;
import reactor.core.publisher.Mono;

public abstract class BeingServiceImpl<T extends Being> implements BeingService<T> {
    public Mono<T> findById(String beingHexId) {
        return Mono.from(getRepo().findById(beingHexId));
    }
    public Mono<Boolean> save(T being) {
        return Mono.from(getRepo().save(being)).thenReturn(true).onErrorReturn(false);
    }
    public Mono<Boolean> deleteById(String beingHexId) {
        return Mono.from(getRepo().delete(beingHexId)).thenReturn(true).onErrorReturn(false);
    }
    protected abstract BeingRepository<T> getRepo();
}