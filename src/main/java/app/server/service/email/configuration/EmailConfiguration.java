package app.server.service.email.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.naming.Named;

@ConfigurationProperties("email")
public interface EmailConfiguration extends Named {
    @ConfigurationProperties("forgot-password")
    interface ForgotPassword {
        @NonNull
        String getTemplate();
        @NonNull
        String getSubject();
    }
}