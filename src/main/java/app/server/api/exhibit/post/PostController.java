package app.server.api.exhibit.post;

import app.server.service.exhibit.post.PostService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("api/v1/posts")
public class PostController { // TODO
    @Inject
    PostService postService;
    @Get
    public HttpResponse<String> feed() {
        return HttpResponse.ok(); // TODO
    }
    @Post
    public HttpResponse<String> upload() {
        return HttpResponse.ok(); // TODO
    }
}