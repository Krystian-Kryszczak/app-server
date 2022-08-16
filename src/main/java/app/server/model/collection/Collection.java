package app.server.model.collection;

import app.server.model.StorageItem;
import org.bson.types.ObjectId;

public abstract class Collection<T> implements StorageItem {
    // TODO
    public ObjectId getId() {
        return null; // TODO
    }
}