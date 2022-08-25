package app.server.storage.repository.being.user;

import app.server.model.being.user.User;
import app.server.storage.repository.being.BeingRepository;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.bson.Document;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Singleton
public interface UserRepository extends BeingRepository<User> {
    @NonNull
    Publisher<User> list();
    Publisher<User> findByEmail(@NonNull @Email String email);
    Publisher<Document> findDocumentByEmail(@NotNull @Email String email);
    Publisher<User> changePassword(@NonNull @NotBlank String hexId, @NonNull @NotBlank String newPassword);
    Flux<String> search(@NonNull String query, @NonNull String userHexId);
}