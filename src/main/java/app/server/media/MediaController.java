package app.server.media;

import app.server.api.ApiController;
import app.server.model.exhibit.ExhibitType;
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
import reactor.core.publisher.Mono;

import java.io.IOException;

public abstract class MediaController<T extends Media<T>> extends ApiController {
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
                    return Mono.from(mediaService.findById(id)).map(this::mediaStream);
                } else { // Media is private
                    if (authentication!=null && clientIsCreator(tuple.getT2(), authentication)) {
                        return Mono.from(mediaService.findById(id)).map(this::mediaStream);
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
    protected Mono<HttpResponse<?>> uploadFile(@NonNull String mediaExhibitId,
    @NonNull Publisher<CompletedFileUpload> file, @NonNull Authentication authentication) {
        boolean isPrivate = false; // temporary
        if (mediaExhibitId.chars().filter(c -> c==':').count()!=1) return Mono.just(HttpResponse.status(HttpStatus.NOT_ACCEPTABLE)); // mediaExhibitId have more than one char ':'
        String[] data = mediaExhibitId.split(":"); ExhibitType type = ExhibitType.getTypeByUrlModifier(data[1]); // exhibitHexId, exhibitTypeString » data | ExhibitType » type
        if (!ObjectId.isValid(data[0])||type==null) return Mono.just(HttpResponse.status(HttpStatus.NOT_ACCEPTABLE)); // exhibitHexId is invalid
        String clientHexId = super.getClientHexId(authentication);
        return Mono.from(getService().findById(data[0]))
            .map(exhibit -> clientIsCreator(clientHexId, authentication))
                .flatMap(isCreator -> isCreator?type.getExhibitService().needMedia(data[0], type) // client is exhibit creator
                    .flatMap(need -> need
                        ? Mono.from(file).flatMap(fileUpload -> { // need
                            try {
                                return Mono.from(getService().save(clientHexId, isPrivate, fileUpload)).map(result -> {
                                    BsonValue value = result.getInsertedId();
                                    if (value!=null) return HttpResponse.created(value.asObjectId().getValue().toHexString());
                                    return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR);
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                                return Mono.just(HttpResponse.serverError());
                            }})
                        : Mono.just(HttpResponse.status(HttpStatus.CONFLICT))) // else
                :Mono.just(HttpResponse.status(HttpStatus.FORBIDDEN))); // client isn't creator
    }
    protected Mono<HttpStatus> delete(@NonNull String id, @NonNull Authentication authentication, @NonNull MediaService<T> mediaService) {
        if (!ObjectId.isValid(id)) return Mono.just(HttpStatus.NOT_ACCEPTABLE);
        return Mono.from(mediaService.findById(id))
            .map(media -> clientIsCreator(media, authentication))
            .flatMap(isCreator -> isCreator || authentication.getRoles().contains("ADMIN")
                ? Mono.from(mediaService.delete(id)).map(result -> result!=null ? HttpStatus.OK : HttpStatus.CONFLICT)
                : Mono.just(HttpStatus.FORBIDDEN)
        ).defaultIfEmpty(HttpStatus.NOT_FOUND)
            .onErrorReturn(HttpStatus.FORBIDDEN);
    }
    protected abstract MediaService<T> getService();
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