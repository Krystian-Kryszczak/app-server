package app.server.model.exhibit.post;

import app.server.storage.repository.exhibit.post.PostRepository;
import jakarta.inject.Inject;

import java.util.concurrent.CompletableFuture;

public class PostTask { // TODO Post »» async & saving system to database
    @Inject
    PostRepository postRepo;
    //@Scheduled(fixedDelay = "5m", initialDelay = "10m")
    void saveLocalDataToBase() {
        CompletableFuture.supplyAsync(() -> {
            // TODO saveRating
            return null;
        });
    }
    void saveRating() {
        // TODO
    }
}