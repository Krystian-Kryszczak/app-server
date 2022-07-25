package app.server.api.exhibit.mix;

import app.server.service.exhibit.post.PostService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

@Controller("api/v1/mix")
public class MixController {
    @Inject
    PostService postService;
    @Get
    public HttpResponse<String> feed() { // TODO
        return HttpResponse.ok("[\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                "    \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                "    \"rating\": 10,\n" +
                "    \"commentsCount\": 2,\n" +
                "    \"shares\": 0,\n" +
                "  },\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 2,\n" +
                "    \"title\": \"qui est esse\",\n" +
                "    \"body\": \"est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla\"\n" +
                "    \"rating\": 8,\n" +
                "    \"commentsCount\": 2,\n" +
                "    \"shares\": 0,\n" +
                "  },\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 3,\n" +
                "    \"title\": \"ea molestias quasi exercitationem repellat qui ipsa sit aut\",\n" +
                "    \"body\": \"et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus vel accusantium quis pariatur\\nmolestiae porro eius odio et labore et velit aut\"\n" +
                "    \"rating\": 17,\n" +
                "    \"commentsCount\": 3,\n" +
                "    \"shares\": 0,\n" +
                "  },\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 4,\n" +
                "    \"title\": \"eum et est occaecati\",\n" +
                "    \"body\": \"ullam et saepe reiciendis voluptatem adipisci\\nsit amet autem assumenda provident rerum culpa\\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\\nquis sunt voluptatem rerum illo velit\"\n" +
                "    \"rating\": 20,\n" +
                "    \"commentsCount\": 4,\n" +
                "    \"shares\": 0,\n" +
                "  },\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 5,\n" +
                "    \"title\": \"nesciunt quas odio\",\n" +
                "    \"body\": \"repudiandae veniam quaerat sunt sed\\nalias aut fugiat sit autem sed est\\nvoluptatem omnis possimus esse voluptatibus quis\\nest aut tenetur dolor neque\"\n" +
                "    \"rating\": 11,\n" +
                "    \"commentsCount\": 2,\n" +
                "    \"shares\": 0,\n" +
                "  }\n" +
                "]");
    }
}

