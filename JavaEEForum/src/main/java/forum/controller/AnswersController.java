package forum.controller;

import forum.auth.CurrentUser;
import forum.dtos.AnswerAddForm;
import forum.entitys.Topic;
import forum.services.answers.AnswersService;
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

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class AnswersController {

    @Autowired
    private TopicsService topicsService;

    @Autowired
    private AnswersService answersService;

    @RequestMapping(value = "/topics/{id}", method = RequestMethod.GET)
    public String allAnswersByTopic(Model model,
                                    @PathVariable("id") int topicId) {

        Topic topic = topicsService.getById(topicId);
        model.addAttribute("topic",topic);
        return "/answers/allByTopic";
    }
    @RequestMapping(value = "/answers/{id}/AddByTopic",
            method = RequestMethod.GET)
    public String addNewAnswerForm(Model model,
                                   HttpServletRequest request,
                                   @PathVariable("id") int id){

        model.addAttribute("addAnswerForm",new AnswerAddForm());
        Topic topic = this.topicsService.getById(id);
        model.addAttribute("topic",topic);
        return "/answers/addByTopic";
    }

    @RequestMapping(value = "/answers/{id}/AddByTopic",method = RequestMethod.POST)
    public String addNewTopicsProcess(@ModelAttribute AnswerAddForm answerAddForm,
                                      HttpServletRequest request,
                                      @PathVariable("id") int id) throws Exception {

        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        int userId = currentUser.getId();
        answerAddForm.setTopicId(id);
        answerAddForm.setAuthorId(userId);
        this.answersService.create(answerAddForm);
        return "redirect:/topics/" + answerAddForm.getTopicId();
    }
}
