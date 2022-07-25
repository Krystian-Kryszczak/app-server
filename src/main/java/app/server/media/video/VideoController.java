package app.server.media.video;

import app.server.service.media.video.VideoService;
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
@Controller("/videos")
public class VideoController { // TODO feed, adding, comments, vote, details,
    // TODO valid file type
    @Inject
    static VideoService videoService;
    // ---------------------------------------------------------------------------------------------------- //
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(value = "/{id}") //get
    Mono<HttpResponse<byte[]>> get(String id) { // TODO check if client can do it
//        Video video = videoService.getVideo(id).orElseThrow();
//        String fileName = video.getName(); //String contentType = //.forFilename(video.getName()); // "application/octet-stream" // MediaType.forExtension(com.google.common.io.Files.getFileExtension(fileName)).orElse(MediaType.APPLICATION_OCTET_STREAM_TYPE)
//        return HttpResponse.ok(video.getBinary().getData())
//            .header("Content-Type", MediaType.forFilename(fileName))
//            .header("Content-disposition", "attachment; filename=\""+fileName+"\"");
        return Mono.from(videoService.getVideo(id)).map(video -> {
            String name = video.getName();
            String fileName = video.getName();
            return HttpResponse.ok(video.getBinary().getData())
                    .header("Content-Type", MediaType.forFilename(fileName))
                    .header("Content-disposition", "attachment; filename=\""+name+"\"");
        });
    }
    @Post(consumes = {MediaType.MULTIPART_FORM_DATA})
    public Mono<HttpResponse<?>> uploadFile(@Part CompletedFileUpload file/*, Session session*/) throws IOException {  // TODO check if client can do it
//        ObjectId objectId = videoService.saveVideo("", file); //session.getId()
//        return HttpResponse.ok(objectId.toHexString());
        String hexId = ""; // creatorHexId // TODO hexId
        return Mono.from(videoService.saveVideo(hexId, file)).map(result -> {
            BsonValue value = result.getInsertedId();
            if (value!=null) return HttpResponse.ok(value.asObjectId().getValue().toHexString());
            return HttpResponse.status(HttpStatus.CONFLICT);
        });
    }
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get
    public HttpResponse<byte[]> downLoadFile() throws IOException {  // TODO check if client can do it
        File file = new File("src/main/resources/public/assets/videos/MP4-1920x1080.mp4");
        return HttpResponse.ok(Files.readAllBytes(file.toPath()))
            .header("Content-type", "application/octet-stream")
            .header("Content-disposition", "attachment; filename=\""+file.getName()+"\"");
    }
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Delete(value = "/{id}")
    Mono<HttpResponse<String>> delete(String id) { // TODO check if client can do it
        return Mono.from(videoService.deleteVideo(id)).map(result -> HttpResponse.status(result!=null ? HttpStatus.OK : HttpStatus.CONFLICT));
        //return videoService.deleteVideo(id) ? HttpResponse.ok() : HttpResponse.status(HttpStatus.CONFLICT);
    }
}