package com.nala.helper.controller;

import com.nala.helper.utils.ResultUtil;
import com.nala.helper.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王政斯
 * @date 2022/1/12 9:10
 * @describe
 */
@Api(tags = "公共部分")
@RestController
@RequestMapping("/nala/common")
public class CommonController {

    private static final String SUCCESS = "success";


    @GetMapping("/check")
    @ApiOperation("检查服务端是否存在，返回success")
    public ResultVO check() {
        return ResultUtil.success(SUCCESS);
    }
}
