package app.server.api.exhibit.post;

import app.server.api.exhibit.ExhibitController;
import app.server.model.exhibit.post.Post;
import app.server.service.exhibit.post.PostService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("api/v1/posts")
public class PostController extends ExhibitController<Post> {
    public PostController(PostService postService) {
        super(postService);
    }
    @Get("/{hexId}")
    public Mono<HttpResponse<Post>> get(String hexId, Authentication authentication) {
        return super.get(hexId, authentication);
    }
    @io.micronaut.http.annotation.Post
    public Mono<HttpResponse<HttpStatus>> add(Post post, Authentication authentication) {
        return super.add(post, authentication);
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
    public Mono<Post> feed(Authentication authentication) {
        return super.feed(authentication);
    }
    @Get("/{hexId}/comments")
    public Flux<String> comments(String hexId, Authentication authentication) {
        return super.comments(hexId, authentication);
    }
}