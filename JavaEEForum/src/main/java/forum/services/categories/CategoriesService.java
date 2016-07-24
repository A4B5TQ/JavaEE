package forum.services.categories;

import forum.entitys.Category;

import java.util.List;

public interface CategoriesService {
    List<Category> getAll();
    Category getById(int Id);
    List<Category> getNextFive(int pageNum);
    List<Category> getPrevFive(int pageNum);
}
