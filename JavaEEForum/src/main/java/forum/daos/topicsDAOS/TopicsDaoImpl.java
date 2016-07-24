package forum.daos.topicsDAOS;

import forum.entitys.Topic;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TopicsDaoImpl implements TopicsDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createTopics(Topic topic) {
        this.em.persist(topic);
        this.em.flush();
    }

    @Override
    public Topic getOneById(int id) {
        Query query =
                this.em.createQuery("select to from Topic as to where to.id = :id");
        query.setParameter("id",id);

        List<Topic> result = query.getResultList();

        if (result.isEmpty()){
            return null;
        }

        return result.get(0);
    }
}
