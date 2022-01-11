package com.nala.helper.controller;


import com.nala.helper.condition.NoteCondition;
import com.nala.helper.pojo.Note;
import com.nala.helper.service.INoteService;
import com.nala.helper.utils.ResultUtil;
import com.nala.helper.vo.NoteListVO;
import com.nala.helper.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResultVO<Boolean> add(String content, Date notifyTime, Integer status, String accId) {
        Note note = new Note();
        note.setContent(content);
        note.setNotifyTime(notifyTime);
        note.setStatus(status);
        note.setAccId(accId);
        return ResultUtil.booleanResult(service.add(note));
    }

    @PostMapping("/edit")
    @ApiOperation("更新备忘")
    public ResultVO<Boolean> edit(@RequestBody Note note) {
        return ResultUtil.booleanResult(service.edit(note));
    }

    @PostMapping("/list")
    @ApiOperation("备忘列表")
    public ResultVO<NoteListVO> list(String keyword, Integer status, String accId) {
        NoteCondition condition = new NoteCondition();
        condition.setStatus(status);
        condition.setAccId(accId);
        condition.setKeyword(keyword);
        return ResultUtil.success(service.list(condition));
    }

}
