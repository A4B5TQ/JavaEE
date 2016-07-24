package forum.daos.answersDAOS;

import forum.entitys.Answer;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AnswersDaoImpl implements AnswersDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Answer answer) {
        this.em.persist(answer);
        this.em.flush();
    }
}
