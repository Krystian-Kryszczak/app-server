package app.server.api.exhibit.mix;

import app.server.model.exhibit.page.Page;
import app.server.service.exhibit.mix.MixService;
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
@Controller("api/v1/mix")
public class MixController { // TODO
    final MixService mixService;
    public MixController(MixService mixService) {
        this.mixService = mixService;
    }
    // ---------------------------------------------------------------------------------------------------- //
    @Get("/{hexId}")
    public Mono<HttpResponse<Page>> get(String hexId, Authentication authentication) {
        return Mono.empty();
    }
    @Post
    public Mono<HttpResponse<HttpStatus>> add(Page exhibit, Authentication authentication) {
        return Mono.empty();
    }
    @Delete("/{hexId}")
    public Mono<HttpResponse<HttpStatus>> delete(String hexId, Authentication authentication) {
        return Mono.empty();
    }
    @Put("/{hexId}")
    public Mono<HttpResponse<HttpStatus>> edit(String hexId, String content, Authentication authentication) {
        return Mono.empty();
    }
    @Get("/feed")
    public Mono<Page> feed(Authentication authentication) {
        return Mono.empty();
    }
    @Get("/{hexId}/comments")
    public Flux<String> comments(String hexId, Authentication authentication) {
        return Flux.empty();
    }
}