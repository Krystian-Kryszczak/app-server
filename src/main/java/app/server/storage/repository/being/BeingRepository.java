package app.server.storage.repository.being;

import app.server.storage.repository.Repository;
import jakarta.inject.Singleton;

@Singleton
public interface BeingRepository<T> extends Repository<T> {}