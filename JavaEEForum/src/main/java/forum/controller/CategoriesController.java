package forum.controller;

import forum.entitys.Category;
import forum.services.categories.CategoriesService;
import forum.services.userDetails.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private UserDetailServiceImpl userDetailService;


    @RequestMapping(value = "/categories/all", method = RequestMethod.GET)
    public String all(Model model,HttpServletRequest request) {
        int page = 1;
        List<Category> result = this.categoriesService.getNextFive(page);
        model.addAttribute("categories",result);
        model.addAttribute("currentPage", page );
        return "/categories/all";
    }
    @RequestMapping(value = "/categories/getNext/{currentPage}", method = RequestMethod.GET)
    public String next(Model model, HttpServletRequest request,
                       @PathVariable("currentPage") int count) {
        count++;
        List<Category> result = this.categoriesService.getNextFive(count);
        model.addAttribute("categories",result);
        model.addAttribute("currentPage",count);
        return "/categories/all";
    }

    @RequestMapping(value = "/categories/getPrev/{currentPage}", method = RequestMethod.GET)
    public String prev(Model model, HttpServletRequest request,
                       @PathVariable("currentPage") int count) {
        --count;
        List<Category> result = this.categoriesService.getPrevFive(count);
        model.addAttribute("categories",result);
        model.addAttribute("currentPage",count);
        return "/categories/all";
    }

    @RequestMapping(value = "/categories/select/{catId}", method = RequestMethod.GET)
    public String select(@PathVariable("catId") int catId,
                         HttpServletRequest request, Model model) throws Exception {


        Category category = this.categoriesService.getById(catId);

        if (category == null){
            throw new Exception("Category does not exist");
        }
        model.addAttribute("category",category);
        return "/topics/allByCategory";
    }
}
