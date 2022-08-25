package app.server.api.exhibit.watch;

import app.server.api.exhibit.ExhibitController;
import app.server.model.exhibit.watch.Watch;
import app.server.service.exhibit.watch.WatchService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("api/v1/watch")
public class WatchController extends ExhibitController<Watch> {
    public WatchController(WatchService watchService) {
        super(watchService);
    }
    @Get("/{hexId}")
    public Mono<Watch> get(String hexId, Authentication authentication) {
        return Mono.from(super.get(hexId, authentication));
    }
    @Post
    public Mono<HttpResponse<HttpStatus>> add(Watch watch, Authentication authentication) {
        return super.add(watch, authentication);
    }
    @Delete("/{exhibitHexId}")
    public Mono<HttpResponse<HttpStatus>> delete(String exhibitHexId, Authentication authentication) {
        return super.delete(exhibitHexId, authentication);
    }
    @Get("/feed")
    public Flux<Watch> feed(Authentication authentication) {
        return super.feed(authentication);
    }
    @Get("/{exhibitHexId}/comments")
    public Flux<String> comments(String exhibitHexId, Authentication authentication) {
        return super.comments(exhibitHexId, authentication);
    }
}