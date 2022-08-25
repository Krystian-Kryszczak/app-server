package app.server.api.exhibit.song;

import app.server.api.exhibit.ExhibitController;
import app.server.model.exhibit.song.Song;
import app.server.service.exhibit.music.SongService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("api/v1/songs")
public class SongController extends ExhibitController<Song> {
    public SongController(SongService songService) {
        super(songService);
    }
    @Get("/{hexId}")
    public Mono<Song> get(String hexId, Authentication authentication) {
        return super.get(hexId, authentication);
    }
    @Post
    public Mono<HttpResponse<HttpStatus>> add(Song song, Authentication authentication) {
        return super.add(song, authentication);
    }
    @Delete("/{exhibitHexId}")
    public Mono<HttpResponse<HttpStatus>> delete(String exhibitHexId, Authentication authentication) {
        return super.delete(exhibitHexId, authentication);
    }
    @Get("/feed")
    public Flux<Song> feed(Authentication authentication) {
        return super.feed(authentication);
    }
    @Get("/{exhibitHexId}/comments")
    public Flux<String> comments(String exhibitHexId, Authentication authentication) {
        return super.comments(exhibitHexId, authentication);
    }
}