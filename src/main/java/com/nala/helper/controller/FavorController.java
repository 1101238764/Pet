package com.nala.helper.controller;


import com.nala.helper.condition.FavorCondition;
import com.nala.helper.service.IFavorService;
import com.nala.helper.vo.FavorVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 收藏表 前端控制器
 * </p>
 *
 * @author 彭术成
 * @since 2021-12-27
 */
@RestController
@Api(tags = "收藏服务")
@RequestMapping("/nala/favor")
public class FavorController {

    @Resource
    private IFavorService service;

    @PostMapping("/collect")
    @ApiOperation("收藏")
    public boolean collect(@RequestBody FavorCondition condition) {
        return service.collect(condition);
    }

    @PostMapping("/remove")
    @ApiOperation("删除收藏")
    public boolean remove(@RequestBody FavorCondition condition) {
        return service.removeById(condition.getId());
    }

    @PostMapping("/edit")
    @ApiOperation("编辑收藏")
    public boolean edit(@RequestBody FavorCondition condition) {
        return service.edit(condition);
    }

    @PostMapping("/list")
    @ApiOperation("收藏列表")
    public List<FavorVO> list(@RequestBody FavorCondition condition) {
        return service.list(condition);
    }


    @PostMapping("/listByType")
    @ApiOperation("分类收藏列表")
    public Map<String, List<FavorVO>> listByType(@RequestBody FavorCondition condition) {
        return service.listByType(condition);
    }
}
