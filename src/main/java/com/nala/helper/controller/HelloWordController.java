package com.nala.helper.controller;


import com.nala.helper.service.IHelloWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiOperation("每日一句")
    public String hello() {
        return service.hello();
    }

    @GetMapping("/put")
    @ApiOperation("爬取句子")
    public boolean saveWords() throws Exception {
        return service.saveWords();
    }


}
