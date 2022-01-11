package com.nala.helper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nala.helper.pojo.Answer;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 彭术成
 * @since 2021-12-22
 */
public interface IAnswerService extends IService<Answer> {

    /**
     * 根据问题id查询
     *
     * @param questionId 问题id
     * @return 答案列表
     */
    List<Answer> queryAnswersById(Integer questionId);
}
