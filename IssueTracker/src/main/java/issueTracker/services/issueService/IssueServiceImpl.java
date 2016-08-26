package issueTracker.services.issueService;

import issueTracker.entities.Issue;
import issueTracker.repositories.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public Issue getOneByTitle(String title) {
        return this.issueRepository.findByTitle(title);
    }

    @Override
    public Issue getOneById(Long id) {
        return this.issueRepository.findOne(id);
    }

    @Override
    public void save(Issue issue) {
        this.issueRepository.save(issue);
    }
}
