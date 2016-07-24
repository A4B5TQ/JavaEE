package forum.daos.categoriesDAOS;

import forum.entitys.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CategoriesDaoImpl implements CategoriesDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Category> getAll() {
        return  this.em.createQuery("SELECT ca FROM Category AS ca").getResultList();
    }

    @Override
    public void create(Category categorie) {
        //TODO
    }

    @Override
    public Category getOneById(int id) {
        Query query = this.em.createQuery("select ca from Category as ca where ca.id = :id");
        query.setParameter("id",id);

        List<Category> result = query.getResultList();

        if (result.isEmpty()){
            return null;
        }

        return result.get(0);
    }

    @Override
    public List<Category> getFive(int countFrom, int countTo) {
        Query query = this.em.createQuery("select ca from Category as ca where ca.id between :countFrom and :countTo");
        query.setParameter("countFrom",countFrom);
        query.setParameter("countTo",countTo);

        List<Category> result = query.getResultList();

        if (result.isEmpty()){
            return null;
        }

        return result;
    }
}
