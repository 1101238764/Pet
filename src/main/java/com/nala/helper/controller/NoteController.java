package com.nala.helper.controller;


import com.nala.helper.condition.NoteCondition;
import com.nala.helper.enums.NoteStatus;
import com.nala.helper.pojo.Note;
import com.nala.helper.service.INoteService;
import com.nala.helper.utils.ResultUtil;
import com.nala.helper.vo.DropdownVO;
import com.nala.helper.vo.NoteVO;
import com.nala.helper.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 备忘录 前端控制器
 * </p>
 *
 * @author 彭术成
 * @since 2022-01-06
 */
@Api(tags = "备忘录")
@RestController
@RequestMapping("/nala/note")
@CrossOrigin
public class NoteController {

    @Resource
    private INoteService service;

    @PostMapping("/add")
    @ApiOperation("创建备忘")
    public ResultVO<Boolean> add(@RequestBody Note note) {
        return ResultUtil.booleanResult(service.add(note));
    }

    @PostMapping("/edit")
    @ApiOperation("更新备忘")
    public ResultVO<Boolean> edit(@RequestBody Note note) {
        return ResultUtil.booleanResult(service.edit(note));
    }

    @PostMapping("/list")
    @ApiOperation("备忘列表")
    public ResultVO<List<NoteVO>> list(@RequestBody NoteCondition condition) {
        return ResultUtil.success(service.list(condition));
    }

    @PostMapping("/count")
    @ApiOperation("不同状态的备忘数量")
    public ResultVO<HashMap<String, Integer>> countByStatus(@RequestParam("accId") String accId) {
        HashMap<String, Integer> count = service.countByStatus(accId);
        return ResultUtil.success(count);
    }

    @PostMapping("/dropdownList")
    @ApiOperation("备忘状态列表")
    public ResultVO<List<DropdownVO>> dropdownList() {
        List<DropdownVO> voList = new ArrayList<>();
        for (NoteStatus status : NoteStatus.values()) {
            DropdownVO vo = DropdownVO.builder()
                    .key(status.getValue())
                    .value(status.getDesc()).build();
            voList.add(vo);
        }
        return ResultUtil.success(voList);
    }

}
