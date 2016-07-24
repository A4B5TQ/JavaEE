package forum.controller;

import forum.auth.CurrentUser;
import forum.dtos.AddTopicForm;
import forum.entitys.Category;
import forum.services.categories.CategoriesService;
import forum.services.topics.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class TopicsController {

    @Autowired
    private TopicsService topicsService;

    @Autowired
    private CategoriesService categoriesService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/categories/{id}/topics/addNewTopics",
            method = RequestMethod.GET)
    public String addNewTopicsForm(Model model,
                                   HttpServletRequest request,
                                   @PathVariable int id){
        model.addAttribute("AddTopicForm",new AddTopicForm());
        List<Category> result = this.categoriesService.getAll();
        model.addAttribute("categories",result);
        Category currentCategory = this.categoriesService.getById(id);
        model.addAttribute("currentCategory",currentCategory);
        return "/topics/addNewTopics";
    }

    @RequestMapping(value = "/categories/{id}/topics/addNewTopics",method = RequestMethod.POST)
    public String addNewTopicsProcess(Model model,
                                      @ModelAttribute AddTopicForm addTopicForm,
                                      HttpServletRequest request,
                                      @PathVariable int id) throws Exception {

        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        int userId = currentUser.getId();
        addTopicForm.setAuthorID(userId);
        this.topicsService.create(addTopicForm);
        return "redirect:/categories/select/" + addTopicForm.getCategoryID();
    }
}
