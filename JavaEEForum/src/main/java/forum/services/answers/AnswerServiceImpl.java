package forum.services.answers;

import forum.daos.answersDAOS.AnswersDao;
import forum.daos.topicsDAOS.TopicsDao;
import forum.daos.usersDAOS.UserDao;
import forum.dtos.AnswerAddForm;
import forum.entitys.Answer;
import forum.entitys.Topic;
import forum.entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class AnswerServiceImpl implements AnswersService {

    @Autowired
    private TopicsDao topicsDao;

    @Autowired
    private AnswersDao answersDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void create(AnswerAddForm form) throws Exception {

        Topic topic = this.topicsDao.getOneById(form.getTopicId());

        if (topic == null) {
            throw new Exception("Cannot answer to a non-existing topic");
        }

        User user = this.userDao.getOneById(form.getAuthorId());

        if (user == null){
            throw new Exception("Cannot answer from an invalid user");
        }

        Answer answer = new Answer();

        answer.setText(form.getText());
        answer.setAuthor(user);
        answer.setTopic(topic);
        answer.setCreateDate(new Date());

        this.answersDao.create(answer);
    }
}
