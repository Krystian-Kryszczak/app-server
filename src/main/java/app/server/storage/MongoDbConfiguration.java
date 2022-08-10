package app.server.storage;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.naming.Named;

@ConfigurationProperties("db")
public interface MongoDbConfiguration extends Named {
    @NonNull
    String getUserCollection();
    @NonNull
    String getGroupCollection();
    @NonNull
    String getPageCollection();
    @NonNull
    String getPostCollection();
    @NonNull
    String getWatchCollection();
    @NonNull
    String getReelCollection();
    @NonNull
    String getSnapCollection();
    @NonNull
    String getStoryCollection();
    @NonNull
    String getSongCollection();
    @NonNull
    String getVideoCollection();
    @NonNull
    String getImageCollection();
    @NonNull
    String getAudioCollection();
    @NonNull
    String getCommunicatorCollection();
    @NonNull
    String getResetPasswordCollection();
    @NonNull
    String getUserHistoryCollection();
    @NonNull
    String getGroupHistoryCollection();
    @NonNull
    String getReportUserCollection();
    @NonNull
    String getReportGroupCollection();
    @NonNull
    String getReportPageCollection();
    @NonNull
    String getReportPostCollection();
    @NonNull
    String getReportWatchCollection();
    @NonNull
    String getReportReelCollection();
    @NonNull
    String getReportSnapCollection();
    @NonNull
    String getReportStoryCollection();
    @NonNull
    String getReportSongCollection();
    @NonNull
    String getReportVideoCollection();
    @NonNull
    String getReportImageCollection();
    @NonNull
    String getReportAudioCollection();
}