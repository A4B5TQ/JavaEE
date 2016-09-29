package issueTracker.services.userService;

import issueTracker.auth.CurrentUser;
import issueTracker.dtos.UserAddForm;
import issueTracker.entities.*;
import issueTracker.enums.Visibility;
import issueTracker.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PriorityRepository priorityRepository;

    public void createUser(UserAddForm userAddForm) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User(userAddForm.getFullname(),
                userAddForm.getUsername(),
                encoder.encode(userAddForm.getPassword()), userAddForm.getEmail());
        userAddForm.getAuthorities().forEach(e -> {
            Authority authority = new Authority(e, user);
            user.getAuthorities().add(authority);
            this.authorityRepository.save(authority);
        });
        State state = new State("FIXED");
        state.setVisibility(Visibility.APPLICATION);
        stateRepository.save(state);
        State state1 = new State("IN PROGRESS");
        state1.setVisibility(Visibility.APPLICATION);
        stateRepository.save(state1);
        State state2 = new State("OPEN");
        state2.setVisibility(Visibility.APPLICATION);
        stateRepository.save(state2);
        Project project = new Project("kurkur");
        Project project1 = new Project("asdasdasd");
        project.setStates(Arrays.asList(state,state1,state2));
        projectRepository.save(project);
        project1.setStates(Arrays.asList(state,state1,state2));
        projectRepository.save(project1);
        Type type = new Type("BUG");
        typeRepository.save(type);
        Type type1 = new Type("BUG!");
        typeRepository.save(type1);
        Type type2 = new Type("BUG@");
        typeRepository.save(type2);
        Priority priority = new Priority("pRIO NESHTO SI");
        priorityRepository.save(priority);
        Issue issue = new Issue("kur", "qj mi kura", project, priority, state1);
        Issue issue1 = new Issue("asdqew", "adasd", project1, priority, state2);
        Issue issue2 = new Issue("kqweqweqw", "fgdfgdfg", project, priority, state);
        issue.setType(type);
        issueRepository.save(issue);
        issue1.setType(type1);
        issueRepository.save(issue1);
        issue2.setType(type2);
        issueRepository.save(issue2);

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User findOneById(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        CurrentUser currentUser = new CurrentUser(user);
        return currentUser;
    }

    @Override
    public User findOneByName(String username) {
        return this.userRepository.findByUsername(username);
    }
}
