package forum.daos.topicsDAOS;

import forum.entitys.Topic;

public interface TopicsDao {
    void createTopics(Topic topic);
    Topic getOneById(int id);
}
