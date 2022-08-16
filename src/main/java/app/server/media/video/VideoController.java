package app.server.media.video;

import app.server.media.MediaController;
import app.server.model.media.video.Video;
import app.server.service.media.video.VideoService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/videos")
public class VideoController extends MediaController<Video> {
    @Inject
    static VideoService videoService;
    // ---------------------------------------------------------------------------------------------------- //
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(value = "/{id}")
    Mono<? extends HttpResponse<byte[]>> get(@PathVariable String id, Authentication authentication) {
        return get(id, authentication, videoService);
    }
    @Post @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Mono<HttpResponse<?>> uploadFile(@Header String exhibitId, @Part Publisher<CompletedFileUpload> file, Authentication authentication) {
        return uploadFile(exhibitId, file, authentication, videoService);
    }
    @Delete(value = "/{id}")
    Mono<HttpStatus> delete(@PathVariable String id, Authentication authentication) {
        return delete(id, authentication, videoService);
    }
}