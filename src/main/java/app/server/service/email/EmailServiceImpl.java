package app.server.service.email;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.email.*;
import io.micronaut.email.template.TemplateBody;
import io.micronaut.views.ModelAndView;
import jakarta.inject.Singleton;

import java.util.Map;

@Singleton
public class EmailServiceImpl implements EmailService {
    private final EmailSender<?, ?> emailSender;
    public EmailServiceImpl(EmailSender<?, ?> emailSender) {
        this.emailSender = emailSender;
    }
    public boolean sendEmail(String to, @NonNull String subject, Map<String, String> model, String templateName) {
        try {
            emailSender.send(Email.builder().to(to).subject(subject)
            .body(new MultipartBody(
                new TemplateBody<>(BodyType.HTML, new ModelAndView<>("email/" + templateName + "/html.vm", model)),
                new TemplateBody<>(BodyType.TEXT, new ModelAndView<>("email/" + templateName + "/text.vm", model))
            )));
        } catch (EmailException e) {
            return false;
        }
        return true;
    }
}