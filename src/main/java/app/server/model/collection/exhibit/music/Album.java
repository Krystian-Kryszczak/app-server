package app.server.model.collection.exhibit.music;

import app.server.model.collection.exhibit.ExhibitCollection;
import app.server.model.exhibit.song.Song;
import app.server.service.report.ReportService;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Introspected
public class Album extends ExhibitCollection<Song> { // TODO
    @Inject
    static ReportService reportService;
    // --------------------------------------------------------------------------- //
    @NonNull @NotBlank
    @BsonProperty("performer")
    final String performer;
    @NonNull @NotBlank
    @BsonProperty("name")
    final String name;
    @NonNull @NotEmpty
    @BsonProperty("songs")
    final List<Song> songs;
    // ---------------------------------------------------------------------------------------------------- //
    @Creator
    @BsonCreator
    public Album(@NonNull @BsonId String hexId, @BsonProperty("rating") int rating, @NonNull @BsonProperty("datetime") LocalDateTime dateTime,
                 @NonNull @NotBlank @BsonProperty("performer") String performer, @NonNull @NotBlank @BsonProperty("name") String name,
                 @NonNull @NotEmpty @BsonProperty("songs") List<Song> songs) {
        //super(hexId, rating, dateTime);
        this.performer = performer;
        this.name = name;
        this.songs = songs;
    }
    public Album(@NonNull @NotBlank @BsonProperty("performer") String performer,
                 @NonNull @NotBlank @BsonProperty("name") String name, @NonNull @NotEmpty @BsonProperty("songs") List<Song> songs) {
        super(); // hexId = null & rating = null
        this.performer = performer;
        this.name = name;
        this.songs = songs;
    }
    // ---------------------------------------------------------------------------------------------------- //
//    @Override
//    public String getUrl() {
//        return abstractUrl("albums");
//    }
//    @Override
//    public Publisher<Boolean> report(@NonNull User user, @NonNull String content) {
//        return null;
//        //return reportService.
//    }
//    @Override
//    public Publisher<Boolean> reportToAdmin(@NonNull User user, @NonNull String content) {
//        return null;
//    }
//    @Override
//    public Flux<String> getHistory(@NonNull User user) {
//        return null;
//    }
    @NonNull
    public String getPerformer() {
        return performer;
    }
    @NonNull
    public String getName() {
        return name;
    }
    @NonNull
    public List<Song> getSongs() {
        return songs;
    }
}