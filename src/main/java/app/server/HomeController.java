package app.server;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller
public class HomeController {
    @Get(consumes = MediaType.TEXT_HTML, processes = MediaType.TEXT_HTML)
    public HttpResponse<String> index() {
        return HttpResponse.ok();
    }
}