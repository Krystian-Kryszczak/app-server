package app.server.model.being.community.group;

import app.server.model.being.community.Community;
import app.server.model.notification.Notification;
import app.server.service.news.NewsService;
import app.server.service.rules.RulesService;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

@Introspected
public class Group extends Community {
    @Inject
    static NewsService newsService;
    @Inject
    static RulesService rulesService;
    @Creator
    @BsonCreator
    public Group(@NonNull @BsonId ObjectId id, @NonNull @BsonProperty("name") String name) {
        super(id, name);
    }
    public Group(@NonNull String name) {
        super(null, name);
    }
    Mono<Boolean> setRules(String content) {
        if (getId()==null) {
            return Mono.error(new RuntimeException("GroupRules not found!"));
        }
        return rulesService.setGroupRulesForTargetId(getId().toHexString(), content);
    }
    Mono<String> getRules() {
        if (getId()==null) {
            return Mono.error(new RuntimeException("GroupRules not found!"));
        }
        return rulesService.getGroupRulesByTargetId(getId().toHexString());
    }
    Mono<Boolean> sendNotification(String content) {
        if (getId()==null) return Mono.just(false);
        return newsService.sendNotificationForGroup(getId().toHexString(), content);
    }
}