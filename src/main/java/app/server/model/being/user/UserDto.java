package app.server.model.being.user;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {
    @NonNull @NotBlank
    final private String hexId;
    @NonNull @NotBlank
    final private String name;
    @Nullable
    final private String lastname;
    public UserDto(@NonNull String hexId, @NonNull String name, @Nullable String lastname) {
        this.hexId = hexId;
        this.name = name;
        this.lastname = lastname;
    }
}