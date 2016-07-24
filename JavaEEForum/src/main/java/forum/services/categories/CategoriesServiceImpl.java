package forum.services.categories;

import forum.daos.categoriesDAOS.CategoriesDao;
import forum.entitys.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesDao categoriesDao;

    @Override
    public List<Category> getAll() {
       return this.categoriesDao.getAll();
    }

    @Override
    public Category getById(int Id) {
       return this.categoriesDao.getOneById(Id);
    }

    @Override
    public List<Category> getNextFive(int pageNum) {
        int countTo = pageNum * 5;
        int countFrom = (countTo - 5) + 1;
        return this.categoriesDao.getFive(countFrom,countTo);
    }

    @Override
    public List<Category> getPrevFive(int pageNum) {
        int countTo = pageNum * 5;
        int countFrom = (countTo - 5) + 1;
        return this.categoriesDao.getFive(countFrom,countTo);
    }
}
