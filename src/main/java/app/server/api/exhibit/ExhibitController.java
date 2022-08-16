package app.server.api.exhibit;

import app.server.model.exhibit.Exhibit;
import app.server.service.exhibit.ExhibitService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.security.authentication.Authentication;
import lombok.Getter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Getter
public abstract class ExhibitController<T extends Exhibit<T>> { // TODO
    final ExhibitService<T> exhibitService;
    public ExhibitController(ExhibitService<T> exhibitService) {
        this.exhibitService = exhibitService;
    }
    public Mono<HttpResponse<HttpStatus>> add(T exhibit, Authentication authentication) {
        return Mono.empty();
    }
    public Mono<HttpResponse<HttpStatus>> delete(String hexId, Authentication authentication) {
        return Mono.empty();
    }
    public Mono<HttpResponse<T>> get(String hexId, Authentication authentication) {
        return Mono.empty();
    }
    public Mono<T> feed(Authentication authentication) {
        return Mono.empty();
    }
    public Mono<HttpResponse<HttpStatus>> edit(String hexId, String content, Authentication authentication) {
        return Mono.empty();
    }
    public Flux<String> comments(String hexId, Authentication authentication) {
        return Flux.empty();
    }
}