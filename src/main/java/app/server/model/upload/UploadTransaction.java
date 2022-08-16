package app.server.model.upload;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Introspected
public class UploadTransaction {
    @Nullable
    @BsonId
    private final ObjectId id;
    @NonNull
    @BsonProperty("exhibitHexId")
    private final String exhibitHexId;
    @Setter
    @BsonProperty("finished")
    private boolean finished;
    @BsonProperty("transactionKeys")
    private List<String> transactionKeys;
    @Creator
    @BsonCreator
    public UploadTransaction(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("exhibitHexId") String exhibitHexId, @BsonProperty("finished") boolean finished) {
        this.id = id;
        this.exhibitHexId = exhibitHexId;
        this.finished = finished;
    }
    public UploadTransaction(@NonNull String exhibitHexId, boolean finished) {
        this.id = null;
        this.exhibitHexId = exhibitHexId;
        this.finished = finished;
    }
    public boolean addTransactionKey(String key) {
        return transactionKeys.add(key);
    }
    public boolean removeTransactionKey(String key) {
        return transactionKeys.remove(key);
    }
}