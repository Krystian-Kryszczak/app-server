package app.server.media;

import app.server.model.media.Media;
import app.server.service.being.user.UserService;
import app.server.service.media.MediaService;
import app.server.service.upload.UploadTransactionService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.security.authentication.Authentication;
import jakarta.inject.Inject;
import org.bson.BsonValue;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

public abstract class MediaController<T extends Media<T>> {
    @Inject
    static UserService userService;
    @Inject
    static UploadTransactionService uploadTransactionService;
    // ---------------------------------------------------------------------------------------------------- //
    protected Mono<? extends HttpResponse<byte[]>> get(@NonNull String id, @Nullable Authentication authentication, @NonNull MediaService<T> mediaService) {
        if (!ObjectId.isValid(id)) return Mono.just(HttpResponse.status(HttpStatus.NOT_ACCEPTABLE));
        return Mono.from(mediaService.getIsPrivateAndCreatorHexId(id))
            .flatMap(tuple -> {
                if (tuple==null) return Mono.just(HttpResponse.status(HttpStatus.NOT_FOUND));
                if (!tuple.getT1()) { // Media is public
                    return Mono.from(mediaService.get(id)).map(this::mediaStream);
                } else { // Media is private
                    if (authentication!=null && clientIsCreator(tuple.getT2(), authentication)) {
                        return Mono.from(mediaService.get(id)).map(this::mediaStream);
                    }
                    return Mono.just(HttpResponse.status(HttpStatus.FORBIDDEN));
                }
            });
    }
    private HttpResponse<byte[]> mediaStream(T media) {
        String fileName = media.getName();
        return HttpResponse.ok(media.getBinary().getData())
            .header("Content-Type", MediaType.forFilename(fileName))
                .header("Content-disposition", "attachment; filename=\""+fileName+"\"");
    }
    protected Mono<HttpResponse<?>> uploadFile(@NonNull String id,
    @NonNull Publisher<CompletedFileUpload> file, @NonNull Authentication authentication, @NonNull MediaService<T> mediaService) {
        boolean isPrivate = false; // temporary
        if (!ObjectId.isValid(id)) return Mono.just(HttpResponse.status(HttpStatus.NOT_ACCEPTABLE));
        if (authentication.getAttributes().get("id") instanceof String) {
            // id - exhibitHexId
            // exhibitTarget
            String clientHexId = (String) authentication.getAttributes().get("id");
            // TODO creatorHexId
            // TODO exhibitIsCompleted
            return Flux.from(file).flatMap(fileUpload -> {
                try {
                    return Mono.from(mediaService.save(clientHexId, isPrivate, fileUpload)).map(result -> {
                        BsonValue value = result.getInsertedId();
                        if (value!=null) return HttpResponse.ok(value.asObjectId().getValue().toHexString());
                        return HttpResponse.status(HttpStatus.CONFLICT);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    return Mono.just(HttpResponse.serverError());
                }
            }).then(Mono.just(HttpResponse.ok()));
        } else {
            return Mono.just(HttpResponse.status(HttpStatus.FORBIDDEN));
        }
    }
    protected Mono<HttpStatus> delete(@NonNull String id, @NonNull Authentication authentication, @NonNull MediaService<T> mediaService) {
        if (!ObjectId.isValid(id)) return Mono.just(HttpStatus.NOT_ACCEPTABLE);
        return Mono.from(mediaService.get(id))
            .map(media -> clientIsCreator(media, authentication))
            .flatMap(isCreator -> isCreator || authentication.getRoles().contains("ADMIN")
                ? Mono.from(mediaService.delete(id)).map(result -> result!=null ? HttpStatus.OK : HttpStatus.CONFLICT)
                : Mono.just(HttpStatus.FORBIDDEN)
        ).defaultIfEmpty(HttpStatus.NOT_FOUND)
            .onErrorReturn(HttpStatus.FORBIDDEN);
    }
    private boolean clientIsCreator(@NonNull T media, @NonNull Authentication authentication) {
        return clientIsCreator(media.getCreatorHexId(), authentication);
    }
    private boolean clientIsCreator(@NonNull String creatorHexId, @NonNull Authentication authentication) {
        Object clientHexIdObj = authentication.getAttributes().get("id");
        if (clientHexIdObj instanceof String) {
            String clientHexId = (String)clientHexIdObj;
            return creatorHexId.equals(clientHexId); // check if client is it media creator
        }
        return false;
    }
}