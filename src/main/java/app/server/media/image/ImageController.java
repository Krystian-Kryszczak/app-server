package app.server.media.image;

import app.server.service.media.image.ImageService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import org.bson.BsonValue;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/images")
public class ImageController { // valid file type & maximum file size exception
    @Inject
    static ImageService imageService;
    // ---------------------------------------------------------------------------------------------------- //
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(value = "/{id}") //get
    Mono<HttpResponse<byte[]>> get(String id) { // TODO check if client can do it
        return Mono.from(imageService.getImage(id)).map(image -> {
            String name = image.getName();
            String contentType = MediaType.APPLICATION_OCTET_STREAM; // "application/octet-stream"
            if (name.endsWith(".png")) {
                contentType = MediaType.IMAGE_PNG;
            } else if (name.endsWith(".gif")) {
                contentType = MediaType.IMAGE_GIF;
            } else if (name.endsWith(".jpeg")) {
                contentType = MediaType.IMAGE_JPEG;
            } else if (name.endsWith(".webp")) {
                contentType = MediaType.IMAGE_WEBP;
            }
            return HttpResponse.ok(image.getBinary().getData())
                .header("Content-Type", contentType)
                    .header("Content-disposition", "attachment; filename=\""+name+"\"");
        });
    }
    @Post(consumes = {MediaType.MULTIPART_FORM_DATA})
    public Mono<HttpResponse<?>> uploadFile(@Part CompletedFileUpload file/*, Session session*/) throws IOException {
        //ObjectId objectId = imageService.saveImage("", file); // session.getId() // TODO hexId
        //FileOutputStream fout = new FileOutputStream("../" + file.getFilename());
        //fout.write(file.getBytes());
        //fout.close();
        String hexId = ""; // creatorHexId // TODO hexId
        return Mono.from(imageService.saveImage(hexId, file)).map(result -> {
            BsonValue value = result.getInsertedId();
            if (value!=null) return HttpResponse.ok(value.asObjectId().getValue().toHexString());
            return HttpResponse.status(HttpStatus.CONFLICT);
        });
    }
    //@Secured(SecurityRule.IS_AUTHENTICATED)
    @Get
    public HttpResponse<byte[]> downLoadFile() throws IOException { // TODO
        File file = new File("src/main/resources/public/assets/images/optical-fibers.jpg");
        return HttpResponse.ok(Files.readAllBytes(file.toPath()))
            .header("Content-type", "application/octet-stream")
            .header("Content-disposition", "attachment; filename=\""+file.getName()+"\"");
    }
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Delete(value = "/{id}")
    Mono<HttpResponse<String>> delete(String id) { // TODO check if client can do it
        return Mono.from(imageService.deleteImage(id)).map(result -> HttpResponse.status(result!=null ? HttpStatus.OK : HttpStatus.CONFLICT));
        //HttpResponse.status(imageService.deleteImage(id). ? HttpStatus.OK : HttpStatus.CONFLICT);
        //return HttpResponse.status(imageService.deleteImage(id). ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
//    @Post(produces = MediaType.TEXT_PLAIN, consumes = MediaType.ALL)
//    @ExecuteOn(TaskExecutors.IO) //
//    String read(@Body InputStream inputStream) throws IOException { //
////        StringBuilder textBuilder = new StringBuilder();
////        try (Reader reader = new BufferedReader(new InputStreamReader
////                (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
////            int c = 0;
////            while ((c = reader.read()) != -1) {
////                textBuilder.append((char) c);
////            }
////        }
//        return "";//IOUtils.readText(new BufferedReader(new InputStreamReader(inputStream))); //
//    }
//    @ExecuteOn(TaskExecutors.IO)
//    @Secured(SecurityRule.IS_ANONYMOUS) // IS_AUTHENTICATED
//    @Post(consumes = MediaType.MULTIPART_FORM_DATA)
//    public HttpResponse<?> upload(@Body CompletedFileUpload file) throws IOException {
//        MultipartBody requestBody = MultipartBody.builder()
//                .addPart("file", file.getFilename(), MediaType.MULTIPART_FORM_DATA_TYPE, file.getBytes())
//                .addPart("file", file.getFilename(), MediaType.MULTIPART_FORM_DATA_TYPE, file.getBytes())
//                .addPart("id", "asdasdsds")
//                .build();
//        Flowable.fromPublisher(file).subscribe(item -> {
//            ObjectId objectId = imageService.saveImage(new Created(null, null), item);
//            id.set(objectId.toHexString());
//        });
//        return HttpResponse.ok(id); // objectId.toHexString()
//    }