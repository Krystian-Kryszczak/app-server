package app.server.security.authentication;

import app.server.security.PasswordEncoder;
import app.server.service.being.user.UserService;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import static io.micronaut.security.authentication.AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH;
import static io.micronaut.security.authentication.AuthenticationFailureReason.USER_NOT_FOUND;

@Singleton
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Inject
    UserService userService;
    @Inject
    PasswordEncoder passwordEncoder;
    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        final String login = authenticationRequest.getIdentity().toString(); // username
        final String password = authenticationRequest.getSecret().toString(); // password
        return Mono.from(userService.findByEmail(login))
            .flatMap(user -> Mono.<AuthenticationResponse>create(
                emitter -> {
                    if (passwordEncoder.matches(password, user.getPassword())) {
                        emitter.success(AuthenticationResponse.success((String) authenticationRequest.getIdentity()));
                        System.out.println(login);
                    } else {
                        emitter.error(AuthenticationResponse.exception(CREDENTIALS_DO_NOT_MATCH));
                    }
                }
        )).switchIfEmpty(Mono.<AuthenticationResponse>just(new AuthenticationFailed(USER_NOT_FOUND)));
    }
}