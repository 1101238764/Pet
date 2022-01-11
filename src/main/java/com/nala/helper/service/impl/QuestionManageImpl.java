package com.nala.helper.service.impl;

import com.nala.helper.pojo.Answer;
import com.nala.helper.pojo.Question;
import com.nala.helper.service.IAnswerService;
import com.nala.helper.service.IQuestionService;
import com.nala.helper.service.QuestionManage;
import com.nala.helper.vo.AnswerVO;
import com.nala.helper.vo.QuestionVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 彭术成
 * @since 2021-12-22
 */
@Service
public class QuestionManageImpl implements QuestionManage {

    @Resource
    private IQuestionService questionService;

    @Resource
    private IAnswerService answerService;

    @Override
    public List<String> ask(String content) {

        return null;
    }

    @Override
    public List<QuestionVO> queryQuestion(String content) {
        List<Question> questionList = questionService.queryQuestions(content);
        List<QuestionVO> voList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionVO vo = QuestionVO.builder()
                    .id(question.getId().toString())
                    .question(question.getQuestion()).build();
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public List<AnswerVO> queryAnswers(Integer questionId) {
        List<Answer> answers = answerService.queryAnswersById(questionId);
        List<AnswerVO> voList = new ArrayList<>();
        for (Answer answer : answers) {
            AnswerVO vo = AnswerVO.builder()
                    .id(answer.getId().toString())
                    .answer(answer.getAnswer())
                    .questionId(answer.getQuestionId()).build();
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public String chat(String content) {
        return null;
    }
}
