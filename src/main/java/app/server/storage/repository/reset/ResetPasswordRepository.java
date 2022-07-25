package app.server.storage.repository.reset;

import app.server.model.reset.ResetCode;
import app.server.storage.repository.Repository;
import io.micronaut.core.annotation.NonNull;
import org.reactivestreams.Publisher;

public interface ResetPasswordRepository extends Repository<ResetCode> {
    @NonNull
    Publisher<ResetCode> list();
    @NonNull
    Publisher<ResetCode> find(@NonNull String code);
}