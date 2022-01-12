package com.nala.helper.controller;


import com.nala.helper.service.IPoemService;
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
@RequestMapping("/nala/poem")
@Api(tags = "诗句")
public class PoemController {

    @Resource
    private IPoemService service;

    @GetMapping("/put")
    @ApiOperation(value = "爬取诗句")
    public boolean saveWords() throws Exception {
        return service.savePoem();
    }

    @GetMapping("/getPoem")
    @ApiOperation("吟诗一首")
    public ResultVO<String> get() {
        return ResultUtil.success(service.get());
    }
}
