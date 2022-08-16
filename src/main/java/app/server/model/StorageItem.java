package app.server.model;

import org.bson.types.ObjectId;

public interface StorageItem {
    ObjectId getId() throws NullPointerException;
}