package app.server.api.exhibit.story;

import app.server.api.exhibit.ExhibitController;
import app.server.model.exhibit.story.Story;
import app.server.service.exhibit.story.StoryService;
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
@Controller("api/v1/stories")
public class StoryController extends ExhibitController<Story> {
    public StoryController(StoryService storyService) {
        super(storyService);
    }
    @Get("/{hexId}")
    public Mono<HttpResponse<Story>> get(String hexId, Authentication authentication) {
        return super.get(hexId, authentication);
    }
    @Post
    public Mono<HttpResponse<HttpStatus>> add(Story story, Authentication authentication) {
        return super.add(story, authentication);
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
    public Mono<Story> feed(Authentication authentication) {
        return super.feed(authentication);
    }
    @Get("/{hexId}/comments")
    public Flux<String> comments(String hexId, Authentication authentication) {
        return super.comments(hexId, authentication);
    }
}