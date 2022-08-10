package app.server.storage.repository.report.exhibit.story;

import app.server.model.exhibit.story.Story;
import app.server.model.report.exhibit.story.StoryReport;
import app.server.storage.repository.report.exhibit.ExhibitReportRepository;

public interface StoryReportRepository extends ExhibitReportRepository<Story, StoryReport> {}