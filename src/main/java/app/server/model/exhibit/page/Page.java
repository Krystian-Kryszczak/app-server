package app.server.model.exhibit.page;

import app.server.model.being.user.User;
import app.server.model.exhibit.Exhibit;
import app.server.service.exhibit.ExhibitService;
import app.server.service.exhibit.page.PageService;
import app.server.service.report.ReportService;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Introspected
public class Page extends Exhibit<Page> {
    @Inject
    static PageService pageService;
    @Creator
    @BsonCreator
    public Page(@NonNull @BsonId ObjectId hexId, @BsonProperty("rating") int rating, @NonNull @BsonProperty("datetime") LocalDateTime dateTime) {
        super(hexId, rating, dateTime);
    }
    public Page() {
        super(); // hexId = null & rating = null
    }
    @Override
    public Publisher<Boolean> report(@NonNull User user, @NonNull String content) {
        if (getId()==null) throw new NullPointerException(getClass().getSimpleName()+" hexId equals null.");
        return getReport().reportPage(getId(), user, content);
    }
    @Override
    public Publisher<Boolean> reportToAdmin(@NonNull User user, @NonNull String content) {
        if (getId()==null) throw new NullPointerException(getClass().getSimpleName()+" hexId equals null.");
        return getReport().reportPageToAdmin(getId(), user, content);
    }
    @Override
    public Flux<String> getHistory(@NonNull String userHexId) {
        return getExhibitHistoryService().getPageHistory(getId().toHexString(), userHexId);
    }
    @Override
    protected ExhibitService<Page> getExhibitService() {
        return pageService;
    }
}