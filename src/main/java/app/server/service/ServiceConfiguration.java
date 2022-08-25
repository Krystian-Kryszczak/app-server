package app.server.service;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.naming.Named;

@ConfigurationProperties("service")
public interface ServiceConfiguration extends Named {
    int getDefaultFeedLimit();
}