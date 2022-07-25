package app.server.service.media.video;

import app.server.model.media.video.Video;
import app.server.service.media.MediaService;
import app.server.storage.repository.media.video.VideoRepository;
import com.mongodb.client.result.InsertOneResult;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.reactivestreams.Publisher;

import java.io.IOException;

@Singleton
public class VideoService extends MediaService<Video> { // TODO this
    @Inject
    VideoRepository videoRepository;
    public Publisher<InsertOneResult> saveVideo(String creatorHexId, @NonNull CompletedFileUpload file) throws IOException {
        return videoRepository.save(new Video(file.getFilename(), creatorHexId, new Binary(BsonBinarySubType.BINARY, file.getBytes())));
    }
    public Publisher<Video> getVideo(String hexId) {
        return videoRepository.findById(hexId);
    }
    public Publisher<Video> deleteVideo(String hexId) {
        return videoRepository.delete(hexId);
    }
//    @Inject
//    private GridFsTemplate gridFsTemplate;
//
//    @Inject
//    private GridFsOperations operations;
//
//    public String addVideo(String title, CompletedFileUpload file) throws IOException {
//        DBObject metaData = new BasicDBObject();
//        metaData.put("type", "video");
//        metaData.put("title", title);
//        ObjectId id = gridFsTemplate.store(
//                file.getInputStream(), file.getName(), file.getContentType(), metaData);
//        return id.toString();
//    }
//
//    public Video getVideo(String id) throws IllegalStateException, IOException {
//        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
//        Video video = new Video();
//        video.setTitle(file.getMetadata().get("title").toString());
//        video.setStream(operations.getResource(file).getInputStream());
//        return video;
//    }
}