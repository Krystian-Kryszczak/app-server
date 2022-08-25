package app.server.service.media;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.multipart.MultipartBody;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

@MicronautTest
public class AudioServiceTest {
    @Inject
    @Client("http://localhost:8080/api/v1/audio")
    HttpClient client;
    @Test
    public void uploadTest() {
        // prepare
        File file = new File("src/main/resources/public/assets/images/optical-fibers.jpg"); //File file = File.createTempFile("data", ".png");
        MultipartBody.Builder requestBody = MultipartBody.builder();
        requestBody.addPart("file", file.getName(), MediaType.forFilename(file.getName()), file).build();
        //when
        HttpRequest<?> request = HttpRequest.POST("/", requestBody).contentType(MediaType.MULTIPART_FORM_DATA_TYPE);
        HttpResponse<String> response = client.toBlocking().exchange(request, String.class);
        System.out.println("Uploaded file hexId » " + response.body());
        //then
        Assertions.assertEquals(response.status(), HttpStatus.OK);
    }
    @Test
    public void getTest(String hexId) {
        //when
        HttpRequest<?> request = HttpRequest.GET("/"+hexId+"/").accept(MediaType.ALL);//.contentType(MediaType.APPLICATION_JSON);
        HttpResponse<String> response = client.toBlocking().exchange(request, String.class);
        //then
        Assertions.assertEquals(response.status(), HttpStatus.OK);
    }
    @Test
    public void fetchTest() {
        //when
        HttpRequest<?> request = HttpRequest.GET("/").accept(MediaType.ALL);//.contentType(MediaType.APPLICATION_JSON);
        HttpResponse<String> response = client.toBlocking().exchange(request, String.class);
        //then
        Assertions.assertEquals(response.status(), HttpStatus.OK);
    }
    @Test
    public void deleteTest(String hexId) {
        //when
        HttpRequest<?> request = HttpRequest.DELETE("/").accept(MediaType.ALL);//.contentType(MediaType.APPLICATION_JSON);
        HttpResponse<String> response = client.toBlocking().exchange(request, String.class);
        //then
        Assertions.assertEquals(response.status(), HttpStatus.OK);
    }
}