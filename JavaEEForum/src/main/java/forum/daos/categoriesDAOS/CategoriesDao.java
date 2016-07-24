package forum.daos.categoriesDAOS;

import forum.entitys.Category;

import java.util.List;

public interface CategoriesDao {
    List<Category> getAll();
    void create(Category categorie);
    Category getOneById(int id);
    List<Category> getFive(int countFrom, int countTo);
}
