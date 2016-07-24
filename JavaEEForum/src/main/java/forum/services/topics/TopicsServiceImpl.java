package forum.services.topics;

import forum.daos.categoriesDAOS.CategoriesDao;
import forum.daos.topicsDAOS.TopicsDao;
import forum.daos.usersDAOS.UserDao;
import forum.dtos.AddTopicForm;
import forum.entitys.Category;
import forum.entitys.Topic;
import forum.entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class TopicsServiceImpl implements TopicsService {

    @Autowired
    private TopicsDao topicsDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CategoriesDao categoriesDao;

    @Override
    public Topic createNewTopic(String title, String text, int catID, int userID) {

        Topic topic = new Topic();

        topic.setTitle(title);
        topic.setText(text);

        return topic;
    }

    @Override
    public Topic getById(int Id) {
        return this.topicsDao.getOneById(Id);
    }

    @Override
    public void create(AddTopicForm form) throws Exception {
        int userId = form.getAuthorID();
        User user = this.userDao.getOneById(userId);

        if (user == null){
            throw new Exception("Cannot add topic from invalid user");
        }

        int categoryId = form.getCategoryID();
        Category category = this.categoriesDao.getOneById(categoryId);

        if (category == null){
            throw new Exception("Cannot add topic to a non-existent category");
        }

        Topic topic = new Topic();
        topic.setTitle(form.getTitle());
        topic.setText(form.getText());
        topic.setAuthor(user);
        topic.setCategory(category);
        topic.setCreateDate(new Date());

        this.topicsDao.createTopics(topic);
    }
}
