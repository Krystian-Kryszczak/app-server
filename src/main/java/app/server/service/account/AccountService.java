package app.server.service.account;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public interface AccountService {
    Publisher<ObjectId> register(@Email String email, @NotNull @Size(min = 2, max = 60) String name, @NotNull @Size(min = 2, max = 60) String lastname,
                                @NotNull @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$") String password);
    Publisher<HttpStatus> forgotPassword(@NotNull String email);
    Publisher<HttpResponse<HttpStatus>> resetPassword(@NonNull String code, @NotNull String password);
}