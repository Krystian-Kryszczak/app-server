package app.server.media.audio;

import app.server.media.MediaController;
import app.server.model.media.audio.Audio;
import app.server.service.media.audio.AudioService;
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
@Controller("/audio")
public class AudioController extends MediaController<Audio> {
    @Inject
    static AudioService audioService;
    // ---------------------------------------------------------------------------------------------------- //
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(value = "/{id}")
    Mono<? extends HttpResponse<byte[]>> get(@PathVariable String id, Authentication authentication) {
        return get(id, authentication, audioService);
    }
    @Post @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Mono<HttpResponse<?>> uploadFile(@Header String id, @Part Publisher<CompletedFileUpload> file, Authentication authentication) {
        return uploadFile(id, file, authentication, audioService);
    }
    @Delete(value = "/{id}")
    Mono<HttpStatus> delete(@PathVariable String id, Authentication authentication) {
        return delete(id, authentication, audioService);
    }
}