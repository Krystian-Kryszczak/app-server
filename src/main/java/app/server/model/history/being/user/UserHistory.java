package app.server.model.history.being.user;

import app.server.model.history.History;
import app.server.model.history.type.HistoryType;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;

@Getter
@Introspected
public class UserHistory extends History {
    @NonNull
    @BsonProperty("historyType")
    final HistoryType.User historyType;
    @Creator
    @BsonCreator
    public UserHistory(@NonNull @BsonId ObjectId id, @NonNull ObjectId userHexId, @NonNull @BsonProperty("historyType") HistoryType.User historyType,
                       @NonNull @BsonProperty("target") ObjectId target, @NonNull @BsonProperty("content") String content) {
        super(id, userHexId, target, content);
        this.historyType = historyType;
    }
    protected UserHistory(@NonNull ObjectId userHexId, @NonNull HistoryType.User historyType, @NonNull ObjectId target, @NonNull String content) {
        super(userHexId, target, content);
        this.historyType = historyType;
    }
    private UserHistory(@NonNull ObjectId userHexId, @NonNull HistoryType.User historyType, @NonNull ObjectId target) {
        super(userHexId, target, null);
        this.historyType = historyType;
    }
    public static UserHistory create(@NonNull @NotBlank ObjectId userHexId, @NonNull @NotBlank HistoryType.User historyType,
        @NonNull @NotBlank ObjectId target, @NonNull @NotBlank String content) {
        return new UserHistory(userHexId, historyType, target, content);
    }
    public static UserHistory create(@NonNull @NotBlank String userHexId, @NonNull @NotBlank HistoryType.User historyType,
                                     @NonNull @NotBlank String target, @NonNull @NotBlank String content) {
        return new UserHistory(new ObjectId(userHexId), historyType, new ObjectId(target), content);
    }
    public static UserHistory create(@NonNull @NotBlank ObjectId userHexId, @NonNull @NotBlank HistoryType.User historyType, @NonNull @NotBlank ObjectId target) {
        return new UserHistory(userHexId, historyType, target);
    }
    public static UserHistory create(@NonNull @NotBlank String userHexId, @NonNull @NotBlank HistoryType.User historyType, @NonNull @NotBlank String target) {
        return new UserHistory(new ObjectId(userHexId), historyType, new ObjectId(target));
    }
}