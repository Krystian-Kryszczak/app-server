package app.server.service.being.community;

import app.server.model.being.community.Community;
import app.server.service.being.BeingServiceImpl;
import app.server.storage.repository.being.community.CommunityRepository;
import reactor.core.publisher.Mono;

public abstract class CommunityServiceImpl<T extends Community> extends BeingServiceImpl<T> {
    public Mono<T> findById(String beingHexId) {
        return Mono.from(getRepo().findById(beingHexId));
    }
    public Mono<Boolean> save(T being) {
        return Mono.from(getRepo().save(being)).thenReturn(true).onErrorReturn(false);
    }
    public Mono<Boolean> deleteById(String beingHexId) {
        return Mono.from(getRepo().delete(beingHexId)).thenReturn(true).onErrorReturn(false);
    }
    protected abstract CommunityRepository<T> getRepo();
}