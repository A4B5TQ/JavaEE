package issueTracker.services.stateService;

import issueTracker.entities.State;

import java.util.List;

public interface StateService {
    List<State> getAll();
    State getOneByName(String name);
    State getOneById(Long id);
    void save(State state);
}
