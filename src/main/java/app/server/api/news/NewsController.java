package app.server.api.news;

import app.server.api.ApiController;
import app.server.service.news.NewsService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import reactor.core.publisher.Mono;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("api/v1/news")
public class NewsController extends ApiController {
    final NewsService newsService;
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }
    @Get
    public Mono<HttpResponse<String>> feed(Authentication authentication) {
        if (!authValidate(authentication)) return Mono.just(HttpResponse.status(HttpStatus.FORBIDDEN));
        return Mono.empty();
    }
}