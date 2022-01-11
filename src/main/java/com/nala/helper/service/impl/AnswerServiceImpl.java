package com.nala.helper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nala.helper.mapper.AnswerMapper;
import com.nala.helper.pojo.Answer;
import com.nala.helper.service.IAnswerService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 彭术成
 * @since 2021-12-22
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements IAnswerService {

    @Override
    public List<Answer> queryAnswersById(Integer questionId) {
        QueryWrapper<Answer> wrapper = new QueryWrapper<>();
        wrapper.eq("question_id",questionId);
        return list(wrapper);
    }
}
