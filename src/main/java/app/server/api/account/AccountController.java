package app.server.api.account;

import app.server.service.account.AccountService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@Secured(SecurityRule.IS_ANONYMOUS) //@PermitAll
@Controller("api/v1/account")
public class AccountController { // TODO cookie, jwt
    @Inject
    AccountService accountService;
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
/*javax.annotation.security.PermitAll
javax.annotation.security.RolesAllowed
javax.annotation.security.DenyAll
jakarta.annotation.security.PermitAll
jakarta.annotation.security.RolesAllowed
jakarta.annotation.security.DenyAll*/