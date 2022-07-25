package app.server.model.being.user;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.naming.Named;

@ConfigurationProperties("user-defaults")
public interface UserDefaultsConfiguration extends Named {
    @NonNull
    String getRole();
}