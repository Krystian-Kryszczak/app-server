package app.server.storage.repository.report.exhibit.post;

import app.server.model.exhibit.post.Post;
import app.server.model.report.exhibit.post.PostReport;
import app.server.storage.repository.report.exhibit.ExhibitReportRepository;

public interface PostReportRepository extends ExhibitReportRepository<Post, PostReport> {}