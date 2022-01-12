package com.nala.helper.controller;

import com.nala.helper.condition.PetCondition;
import com.nala.helper.service.IPetService;
import com.nala.helper.utils.ResultUtil;
import com.nala.helper.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共功能
 *
 * @author 王政斯
 * @date 2022/1/12 9:10
 */
@Api(tags = "公共部分")
@RestController
@RequestMapping("/nala/common")
public class CommonController {

    @Resource
    private IPetService petService;

    @GetMapping("/check")
    @ApiOperation("检查服务端是否存在，返回success")
    public ResultVO<Boolean> check() {
        return ResultUtil.success(true);
    }

    @PostMapping("/updatePetName")
    @ApiOperation("更新宠物名称")
    public ResultVO<Boolean> updatePetName(@RequestBody PetCondition condition) {
        return ResultUtil.success(petService.updatePetName(condition));
    }

    @PostMapping("/switchPet")
    @ApiOperation("宠物开关（0关闭 1开启）")
    public ResultVO<Boolean> switchPet(@RequestBody PetCondition condition) {
        return ResultUtil.success(petService.switchShow(condition));
    }
}
