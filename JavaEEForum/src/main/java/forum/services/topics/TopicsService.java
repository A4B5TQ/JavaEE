package forum.services.topics;

import forum.dtos.AddTopicForm;
import forum.entitys.Topic;

public interface TopicsService {
    Topic createNewTopic(String title, String Text, int catID, int userID);
    Topic getById(int Id);
    void create(AddTopicForm form) throws Exception;
}
