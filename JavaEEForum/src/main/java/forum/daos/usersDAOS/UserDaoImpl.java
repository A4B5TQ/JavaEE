package forum.daos.usersDAOS;

import forum.entitys.Authority;
import forum.entitys.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(User user, Authority authority) {
        this.em.persist(user);
        this.em .persist(authority);
        this.em.flush();
    }

    @Override
    public User getOneById(int id) {
        Query query = this.em.createQuery("SELECT us FROM User us WHERE us.id = :id");
        query.setParameter("id",id);

        List<User> result = query.getResultList();

        if (result.isEmpty()){
            return null;
        }

        User anotherUser = result.get(0);

        return anotherUser;
    }

    @Override
    public User getOneByNameAndPassword(String username,String password) {
        Query query = this.em.createQuery("select us from User as us where us.name = :Name and us.password = :Password");
        query.setParameter("Name", username);
        query.setParameter("Password", password);

        List<User> result = query.getResultList();

        if (result.isEmpty()) {
            return null;
        }

        User anotherUser = result.get(0);

        return anotherUser;
    }

    @Override
    public User getOneByName(String username) {
        Query query = this.em.createQuery("select us from User as us where us.name = :Name");
        query.setParameter("Name", username);

        List<User> result = query.getResultList();

        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }
}
