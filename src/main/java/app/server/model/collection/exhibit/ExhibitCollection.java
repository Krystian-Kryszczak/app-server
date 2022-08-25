package app.server.model.collection.exhibit;

import app.server.model.collection.Collection;
import app.server.model.exhibit.Exhibit;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.NonNull;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedList;
import java.util.List;

@Getter
public abstract class ExhibitCollection<T extends Exhibit<T>> extends Collection<T> {
    @NonNull @NotBlank
    @BsonProperty("performer")
    final String performer;
    @NonNull @NotBlank
    @BsonProperty("name")
    final String name;
    @NonNull @NotEmpty
    @BsonProperty("items")
    final List<T> items;
    @Creator
    @BsonCreator
    public ExhibitCollection(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("performer") String performer, @NonNull @BsonProperty("name") String name, @NonNull @BsonProperty("items") List<T> items) {
        super(id);
        this.performer = performer;
        this.name = name;
        this.items = items;
    }
    public ExhibitCollection(@NonNull String performer, @NonNull String name, @NonNull List<T> items) {
        super(null);
        this.performer = performer;
        this.name = name;
        this.items = items;
    }
    public ExhibitCollection(@NonNull String performer, @NonNull String name) {
        super(null);
        this.performer = performer;
        this.name = name;
        this.items = new LinkedList<>();
    }
    public T getItem(int i) {
        return items.get(i);
    }
    public boolean addItem(T item) {
        return items.add(item);
    }
    public boolean removeItem(T item) {
        return items.remove(item);
    }
    public T removeItem(int i) {
        return items.remove(i);
    }
}