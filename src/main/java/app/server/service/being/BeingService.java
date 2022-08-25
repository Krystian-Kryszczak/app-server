package app.server.service.being;

import app.server.model.being.Being;
import reactor.core.publisher.Mono;

public interface BeingService<T extends Being> {
    Mono<T> findById(String beingHexId);
    Mono<Boolean> save(T being);
    Mono<Boolean> deleteById(String beingHexId);
}