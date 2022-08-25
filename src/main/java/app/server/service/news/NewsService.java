package app.server.service.news;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface NewsService {
    Mono<Map<String, String>> getNews(String hexId);
    Mono<Boolean> sendNotificationForUser(String userHexId, String content);
    Mono<Boolean> sendNotificationForGroup(String groupHexId, String content);
}