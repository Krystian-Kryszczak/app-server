package app.server.service.email;

import io.micronaut.core.annotation.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Map;

public interface EmailService {
    boolean sendEmail(@Email String to, @NonNull String subject, Map<String, String> model, @NotBlank String templateName);
}