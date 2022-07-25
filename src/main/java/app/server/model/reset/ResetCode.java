package app.server.model.reset;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import lombok.Builder;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
@Introspected
public class ResetCode {
    @NonNull
    @NotBlank
    @BsonProperty("code")
    private final String code;
    @NonNull
    @NotBlank
    @BsonProperty("userId")
    private final String userId;
    @NonNull
    @NotBlank
    @BsonProperty("dateTime")
    private final LocalDateTime expiration;
    @Creator
    @BsonCreator
    public ResetCode(@NonNull @BsonProperty("code") String code, @NonNull @BsonProperty("userId") String userId, @NonNull @BsonProperty("expiration") LocalDateTime expirationDateTime) {
        this.code = code;
        this.userId = userId;
        this.expiration = expirationDateTime;
    }
    @NonNull
    public String getCode() {
        return code;
    }
    @NonNull
    public String getUserId() {
        return userId;
    }
    @NonNull
    public LocalDateTime getExpiration() {
        return expiration;
    }
}