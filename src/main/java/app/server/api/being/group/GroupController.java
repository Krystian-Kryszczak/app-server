package app.server.api.being.group;

import app.server.api.being.BeingController;
import app.server.model.being.group.Group;
import app.server.service.being.group.GroupService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import reactor.core.publisher.Mono;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("api/v1/groups")
public class GroupController extends BeingController<Group> {
    @Inject
    static GroupService groupService;
    // ---------------------------------------------------------------------------------------------------- //
    @Get
    Mono<HttpResponse<String>> feed() { // TODO recommendation groups
        return Mono.just(HttpResponse.ok());
    }
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{id}") //get
    Mono<HttpResponse<String>> get(String id) { // TODO get group by group id
        return Mono.just(HttpResponse.ok());
    }
}
// Group -> chat, multimedia & files, events