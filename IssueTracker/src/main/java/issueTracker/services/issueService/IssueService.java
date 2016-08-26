package issueTracker.services.issueService;

import issueTracker.entities.Issue;

public interface IssueService {
    Issue getOneByTitle(String title);
    Issue getOneById(Long id);
    void save(Issue issue);
}
