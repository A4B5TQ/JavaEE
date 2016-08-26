package issueTracker.controllers;

import issueTracker.entities.Issue;
import issueTracker.entities.State;
import issueTracker.services.issueService.IssueService;
import issueTracker.services.stateService.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private StateService stateService;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    String dragAndDrop(@RequestParam(value = "stateId") String stateId,
                       @RequestParam(value = "issueId") String issueId) {
        Issue issue = this.issueService.getOneById(Long.parseLong(issueId));
        State newState = this.stateService.getOneById(Long.parseLong(stateId));
        issue.setCurrentState(newState);
        newState.getIssues().add(issue);
        this.issueService.save(issue);
        this.stateService.save(newState);

        return String.format("kur");
    }
}
