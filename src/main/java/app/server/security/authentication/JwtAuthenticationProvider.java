package app.server.security.authentication;

import app.server.security.encoder.PasswordEncoder;
import app.server.service.being.user.UserService;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.Map;

import static io.micronaut.security.authentication.AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH;
import static io.micronaut.security.authentication.AuthenticationFailureReason.USER_NOT_FOUND;
import static io.micronaut.security.authentication.AuthenticationFailureReason.CUSTOM;

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
                        ObjectId userId = user.getId();
                        if (userId!=null) {
                            emitter.success(AuthenticationResponse.success(login, Map.of("id", user.getId().toHexString())));
                        } else {
                            emitter.error(AuthenticationResponse.exception(CUSTOM));
                        }
                    } else {
                        emitter.error(AuthenticationResponse.exception(CREDENTIALS_DO_NOT_MATCH));
                    }
                }
        )).switchIfEmpty(Mono.<AuthenticationResponse>just(new AuthenticationFailed(USER_NOT_FOUND)));
    }
}