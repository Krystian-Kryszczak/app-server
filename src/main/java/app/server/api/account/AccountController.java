package app.server.api.account;

import app.server.service.account.AccountService;
import app.server.service.news.NewsService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("api/v1/account")
public class AccountController {
    @Inject
    AccountService accountService;
    @Inject
    NewsService newsService;
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/news")
    public Mono<HttpResponse<Map<String, String>>> getNews(Authentication authentication) {
        if (!(authentication.getAttributes().get("id") instanceof String)) {
            return Mono.just(HttpResponse.status(HttpStatus.UNAUTHORIZED));
        }
        String hexId = (String)authentication.getAttributes().get("id"); // clientHexId
        return newsService.getNews(hexId).map(HttpResponse::ok);
    }
    @Post(value = "/register", consumes = MediaType.APPLICATION_JSON)
    public Mono<HttpResponse<HttpStatus>> register(@NotNull String email, @NotNull String name, @NotNull String lastname, @NotNull String password) {
        return Mono.from(accountService.register(email, name, lastname, password))
            .mapNotNull(id -> HttpResponse.status(id!=null ? HttpStatus.CREATED : HttpStatus.CONFLICT));
    }
    @Post("/forgot-password")
    public Mono<HttpResponse<HttpStatus>> forgotPassword(@NonNull @NotNull String email) {
        return Mono.from(accountService.forgotPassword(email)).map(HttpResponse::status);
    }
    @Post("/reset-password/{code}")
    public Mono<HttpResponse<HttpStatus>> resetPassword(@PathVariable String code, @NotNull String password) {
        return Mono.from(accountService.resetPassword(code, password));
    }
}