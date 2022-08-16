package app.server.service.history.exhibit;

import reactor.core.publisher.Flux;

public interface ExhibitHistoryService {
    Flux<String> getPageHistory(String pageHexId, String userHexId);
    Flux<String> getPostHistory(String postHexId, String userHexId);
    Flux<String> getReelHistory(String reelHexId, String userHexId);
    Flux<String> getSnapHistory(String snapHexId, String userHexId);
    Flux<String> getSongHistory(String songHexId, String userHexId);
    Flux<String> getStoryHistory(String storyHexId, String userHexId);
    Flux<String> getWatchHistory(String watchHexId, String userHexId);
}