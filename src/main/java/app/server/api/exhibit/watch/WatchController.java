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
import io.micronaut.http.annotation.Put;
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
    public Mono<HttpResponse<Watch>> get(String hexId, Authentication authentication) {
        return super.get(hexId, authentication);
    }
    @Post
    public Mono<HttpResponse<HttpStatus>> add(Watch watch, Authentication authentication) {
        return super.add(watch, authentication);
    }
    @Delete("/{hexId}")
    public Mono<HttpResponse<HttpStatus>> delete(String hexId, Authentication authentication) {
        return super.delete(hexId, authentication);
    }
    @Put("/{hexId}")
    public Mono<HttpResponse<HttpStatus>> edit(String hexId, String content, Authentication authentication) {
        return super.edit(hexId, content, authentication);
    }
    @Get("/feed")
    public Mono<Watch> feed(Authentication authentication) {
        return super.feed(authentication);
    }
    @Get("/{hexId}/comments")
    public Flux<String> comments(String hexId, Authentication authentication) {
        return super.comments(hexId, authentication);
    }
}