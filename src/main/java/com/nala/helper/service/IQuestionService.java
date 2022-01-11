package com.nala.helper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nala.helper.pojo.Question;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 彭术成
 * @since 2021-12-22
 */
public interface IQuestionService extends IService<Question> {

    /**
     * 根据关键字查询
     *
     * @param content 关键内容
     * @return 问题列表
     */
    List<Question> queryQuestions(String content);
}
