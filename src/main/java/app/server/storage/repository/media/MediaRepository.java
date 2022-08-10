package app.server.storage.repository.media;

import app.server.model.media.Media;
import app.server.storage.repository.Repository;

public interface MediaRepository<T extends Media<T>> extends Repository<T> {}