package issueTracker.services.stateService;

import issueTracker.entities.State;
import issueTracker.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository stateRepository;


    @Override
    public List<State> getAll() {
        return this.stateRepository.findAll();
    }

    @Override
    public State getOneByName(String name) {
        return this.stateRepository.findByName(name);
    }

    @Override
    public State getOneById(Long id) {
        return this.stateRepository.findOne(id);
    }

    @Override
    public void save(State state) {
        this.stateRepository.saveAndFlush(state);
    }
}
