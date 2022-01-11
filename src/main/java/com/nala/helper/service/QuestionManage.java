package com.nala.helper.service;

import com.nala.helper.vo.AnswerVO;
import com.nala.helper.vo.QuestionVO;
import java.util.List;

/**
 * todo 描述当前类的功能
 *
 * @author 彭术成
 * @since 2021/12/22
 */
public interface QuestionManage {

    /**
     * 提问
     *
     * @param content 问题内容
     * @return 答案列表
     */
    List<String> ask(String content);

    /**
     * 查询问题列表
     *
     * @param content 关键内容
     * @return 问题列表
     */
    List<QuestionVO> queryQuestion(String content);

    /**
     * 根据问题id查询
     *
     * @param questionId 问题id
     * @return 答案列表
     */
    List<AnswerVO> queryAnswers(Integer questionId);

    /**
     * 聊天
     *
     * @param content 内容
     * @return 返回内容
     */
    String chat(String content);
}
