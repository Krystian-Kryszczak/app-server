package app.server.model.being.community.page;

import app.server.model.being.community.Community;
import app.server.service.being.community.page.PageService;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Introspected
public class Page extends Community {
    @Inject
    static PageService pageService;
    @Creator
    @BsonCreator
    public Page(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("name") String name) {
        super(id, name);
    }
//    public Page() {
//        super(); // hexId = null & rating = null
//    }
//    @Override
//    public Publisher<Boolean> report(@NonNull User user, @NonNull String content) {
//        if (getId()==null) throw new NullPointerException(getClass().getSimpleName()+" hexId equals null.");
//        return getReport().reportPage(getId(), user, content);
//    }
//    @Override
//    public Publisher<Boolean> reportToAdmin(@NonNull User user, @NonNull String content) {
//        if (getId()==null) throw new NullPointerException(getClass().getSimpleName()+" hexId equals null.");
//        return getReport().reportPageToAdmin(getId(), user, content);
//    }
//    @Override
//    public Flux<String> getHistory(@NonNull String userHexId) {
//        return getExhibitHistoryService().getPageHistory(getId().toHexString(), userHexId);
//    }
//    @Override
//    protected ExhibitService<Page> getExhibitService() {
//        return pageService;
//    }
}