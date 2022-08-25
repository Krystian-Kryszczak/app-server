package app.server.api.being.community.page;

import app.server.api.being.community.CommunityController;
import app.server.model.being.community.page.Page;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("api/v1/pages")
public class PageController extends CommunityController<Page> {
//    public PageController(PageService pageService) {
//        super(pageService);
//    }
//    @Get("/{hexId}")
//    public Mono<HttpResponse<Page>> get(String hexId, Authentication authentication) {
//        return super.get(hexId, authentication);
//    }
//    @Post
//    public Mono<HttpResponse<HttpStatus>> add(Page page, Authentication authentication) {
//        return super.add(page, authentication);
//    }
//    @Delete("/{hexId}")
//    public Mono<HttpResponse<HttpStatus>> delete(String hexId, Authentication authentication) {
//        return super.delete(hexId, authentication);
//    }
//    @Put("/{hexId}")
//    public Mono<HttpResponse<HttpStatus>> edit(String hexId, String content, Authentication authentication) {
//        return super.edit(hexId, content, authentication);
//    }
//    @Get("/feed")
//    public Mono<Page> feed(Authentication authentication) {
//        return super.feed(authentication);
//    }
//    @Get("/{hexId}/comments")
//    public Flux<String> comments(String hexId, Authentication authentication) {
//        return super.comments(hexId, authentication);
//    }
}