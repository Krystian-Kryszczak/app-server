package app.server.storage.repository.media;

import app.server.model.media.Media;
import app.server.storage.repository.Repository;
import org.reactivestreams.Publisher;
import reactor.util.function.Tuple2;

public interface MediaRepository<T extends Media<T>> extends Repository<T> {
    Publisher<Boolean> isPrivate(String hexId);
    Publisher<Tuple2<Boolean, String>> getIsPrivateAndCreatorHexId(String hexId);
}