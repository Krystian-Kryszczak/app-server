package app.server.storage.repository;

import com.mongodb.client.result.InsertOneResult;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.bson.Document;
import org.reactivestreams.Publisher;

import javax.validation.Valid;

@Singleton
public interface Repository<T> {
    Publisher<InsertOneResult> save(@NonNull @Valid T value);
    Publisher<T> findById(@NonNull String hexId);
    Publisher<Document> findDocumentById(@NonNull String hexId);
    Publisher<T> delete(@NonNull @Valid String hexId);
}