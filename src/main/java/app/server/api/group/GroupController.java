package app.server.api.group;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.security.Principal;

@Controller("api/v1/groups/")
public class GroupController {
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/") //feed
    HttpResponse<String> feed() {
        // TODO recommendation groups
        return HttpResponse.ok();
    }
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{id}") //get
    HttpResponse<String> get(String id) {
        // TODO get group by group id
        return HttpResponse.ok();
    }
    //@Post("/") //upload
    @Post(value = "/", consumes = MediaType.MULTIPART_FORM_DATA, produces = MediaType.TEXT_PLAIN)
    HttpResponse<String> upload(Principal principal) {
        // TODO
        return HttpResponse.ok();
    }
    // TODO //
    /*

    get group
    create group

    */
    // TODO this
}