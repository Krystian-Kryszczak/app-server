package app.server.model.history.user;

import app.server.model.history.History;
import app.server.model.history.type.HistoryType;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.validation.constraints.NotBlank;

@Getter
@Introspected
public class UserHistory extends History {
    @NonNull
    @BsonProperty("historyType")
    final HistoryType.User historyType;
    @Creator
    @BsonCreator
    public UserHistory(@NonNull @BsonId String hexId, @NonNull String userHexId, @NonNull @BsonProperty("historyType") HistoryType.User historyType,
           @NonNull @BsonProperty("target") String target, @NonNull @BsonProperty("content") String content) {
        super(hexId, userHexId, target, content);
        this.historyType = historyType;
    }
    protected UserHistory(@NonNull String userHexId, @NonNull HistoryType.User historyType, @NonNull String target, @NonNull String content) {
        super(userHexId, target, content);
        this.historyType = historyType;
    }
    private UserHistory(@NonNull String userHexId, @NonNull HistoryType.User historyType, @NonNull String target) {
        super(userHexId, target, null);
        this.historyType = historyType;
    }
    public static UserHistory create(@NonNull @NotBlank String userHexId, @NonNull @NotBlank HistoryType.User historyType,
        @NonNull @NotBlank String target, @NonNull @NotBlank String content) {
        return new UserHistory(userHexId, historyType, target, content);
    }
    public static UserHistory create(@NonNull @NotBlank String userHexId, @NonNull @NotBlank HistoryType.User historyType, @NonNull @NotBlank String target) {
        return new UserHistory(userHexId, historyType, target);
    }
}