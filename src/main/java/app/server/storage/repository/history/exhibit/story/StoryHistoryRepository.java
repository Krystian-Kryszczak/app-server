package app.server.storage.repository.history.exhibit.story;

import app.server.model.exhibit.story.Story;
import app.server.model.history.exhibit.story.StoryHistory;
import app.server.storage.repository.history.exhibit.ExhibitHistoryRepository;

public interface StoryHistoryRepository extends ExhibitHistoryRepository<Story, StoryHistory> {}