package app.server.model.being.user;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;

public class UserDto {
    //@NonNull @NotBlank final private String name;
    //@Nullable final private String lastname;
    //@BsonCreator
    public UserDto(String hexId, @NonNull String name, @Nullable String lastname) {
        //super(hexId);
        //this.name = name;
        //this.lastname = lastname;
    }
}