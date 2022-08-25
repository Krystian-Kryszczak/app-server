package app.server.api.being.community.group;

import app.server.api.being.community.CommunityController;
import app.server.model.being.community.group.Group;
import app.server.service.being.community.group.GroupService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import reactor.core.publisher.Mono;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("api/v1/groups")
public class GroupController extends CommunityController<Group> {
    @Inject
    static GroupService groupService;
    // ---------------------------------------------------------------------------------------------------- //
    @Get
    Mono<HttpResponse<String>> feed(Authentication authentication) {
        if (!authValidate(authentication)) return Mono.just(HttpResponse.status(HttpStatus.FORBIDDEN));
        return groupService.groupsRecommendation(getClientHexId(authentication)).map(HttpResponse::ok);
    }
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{id}")
    Mono<HttpResponse<Group>> get(String id) {
        return groupService.findById(id).map(HttpResponse::ok);
    }
}