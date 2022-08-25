package app.server.api.exhibit;

import app.server.api.ApiController;
import app.server.model.comment.sorting.SortBy;
import app.server.model.exhibit.Exhibit;
import app.server.service.exhibit.ExhibitService;
import com.google.gson.Gson;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.security.authentication.Authentication;
import lombok.Getter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Getter
public abstract class ExhibitController<T extends Exhibit<T>> extends ApiController {
    final ExhibitService<T> exhibitService; Gson gson = new Gson();
    public ExhibitController(ExhibitService<T> exhibitService) {
        this.exhibitService = exhibitService;
    }
    public Mono<HttpResponse<HttpStatus>> add(T exhibit, Authentication authentication) {
        if (!authValidate(authentication)) return Mono.empty();
        return exhibitService.uploadByUser(exhibit, getClientHexId(authentication)).map(success -> success?HttpResponse.ok():HttpResponse.status(HttpStatus.NOT_ACCEPTABLE));
    }
    public Mono<HttpResponse<HttpStatus>> delete(String exhibitHexId, Authentication authentication) {
        if (!authValidate(authentication)) return Mono.empty();
        return exhibitService.deleteByUser(exhibitHexId, getClientHexId(authentication)).map(success -> success?HttpResponse.ok():HttpResponse.status(HttpStatus.NOT_ACCEPTABLE));
    }
    public Mono<T> get(String hexId, Authentication authentication) {
        if (!authValidate(authentication)) return Mono.empty();
        return exhibitService.getForUser(hexId);
    }
    public Flux<T> feed(Authentication authentication) {
        if (!authValidate(authentication)) return Flux.empty();
        return exhibitService.getFeedForUser(getClientHexId(authentication));
    }
    public Flux<String> comments(String exhibitHexId, Authentication authentication) {
        if (!authValidate(authentication)) return Flux.empty();
        return exhibitService.getComments(getClientHexId(authentication), exhibitHexId, SortBy.latest).map(gson::toJson);
    }
}