package com.nala.helper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nala.helper.mapper.QuestionMapper;
import com.nala.helper.pojo.Question;
import com.nala.helper.service.IQuestionService;
import java.util.List;
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
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Override
    public List<Question> queryQuestions(String content) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.like("question", content);
        return list(wrapper);
    }
}
