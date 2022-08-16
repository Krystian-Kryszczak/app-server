package app.server.api.being.group;

import app.server.api.being.BeingController;
import app.server.model.being.group.Group;
import io.micronaut.http.annotation.Controller;

@Controller("api/v1/groups")
public class GroupController extends BeingController<Group> {
//    @Secured(SecurityRule.IS_ANONYMOUS)
//    @Get("/") //feed
//    HttpResponse<String> feed() {
//        // TODO recommendation groups
//        return HttpResponse.ok();
//    }
//    @Secured(SecurityRule.IS_ANONYMOUS)
//    @Get("/{id}") //get
//    HttpResponse<String> get(String id) {
//        // TODO get group by group id
//        return HttpResponse.ok();
//    }
//    //@Post("/") //upload
//    @Post(value = "/", consumes = MediaType.MULTIPART_FORM_DATA, produces = MediaType.TEXT_PLAIN)
//    HttpResponse<String> upload(Principal principal) {
//        // TODO
//        return HttpResponse.ok();
//    }
    // TODO this
}
// Group -> chat, multimedia & files, events