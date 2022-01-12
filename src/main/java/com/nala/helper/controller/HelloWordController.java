package com.nala.helper.controller;


import com.nala.helper.service.IHelloWordService;
import com.nala.helper.utils.ResultUtil;
import com.nala.helper.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 彭术成
 * @since 2021-12-30
 */
@RestController
@RequestMapping("/nala/helloWord")
@Api(tags = "问候语")
public class HelloWordController {

    @Resource
    private IHelloWordService service;

    @GetMapping("/hello")
    @ApiOperation("早安")
    public ResultVO hello() {
        return ResultUtil.success(service.hello());
    }

    @GetMapping("/put")
    @ApiOperation(value = "爬取句子")
    public ResultVO saveWords() throws Exception {
        return ResultUtil.success(service.saveWords());
    }




}
