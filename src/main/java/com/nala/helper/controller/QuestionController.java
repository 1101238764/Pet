package com.nala.helper.controller;


import com.nala.helper.api.out.TuringOsApi;
import com.nala.helper.service.QuestionManage;
import com.nala.helper.utils.ResultUtil;
import com.nala.helper.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 聊天 服务
 *
 * @author 彭术成
 * @since 2021-12-22
 */
@RestController
@Api(tags = "问答服务")
@RequestMapping("/nala/question")
public class QuestionController {

    @Resource
    private QuestionManage questionService;

    @Resource
    private TuringOsApi turingOsApi;

    @PostMapping("/ask")
    @ApiOperation(value = "提问")
    public ResultVO<List<String>> ask(@RequestBody String question) {
        return ResultUtil.success(questionService.ask(question));
    }

    @PostMapping("/chat")
    @ApiOperation("聊天")
    public ResultVO<String> chat(@RequestBody String content) {
        return ResultUtil.success(turingOsApi.testChat(content));
    }
}
