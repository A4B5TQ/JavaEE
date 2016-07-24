package forum.services.answers;

import forum.dtos.AnswerAddForm;

public interface AnswersService {
    void create(AnswerAddForm form) throws Exception;
}
