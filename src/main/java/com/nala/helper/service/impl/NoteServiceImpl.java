package com.nala.helper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nala.helper.condition.NoteCondition;
import com.nala.helper.constants.Symbol;
import com.nala.helper.enums.NoteStatus;
import com.nala.helper.mapper.NoteMapper;
import com.nala.helper.pojo.Note;
import com.nala.helper.service.INoteService;
import com.nala.helper.vo.NoteListVO;
import com.nala.helper.vo.NoteVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 备忘录 服务实现类
 * </p>
 *
 * @author 彭术成
 * @since 2022-01-06
 */
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements INoteService {

    @Override
    public boolean add(Note note) {
        note.setCreateTime(new Date());
        note.setUpdateTime(new Date());
        return save(note);
    }

    @Override
    public boolean edit(Note note) {
        note.setUpdateTime(new Date());
        return updateById(note);
    }

    @Override
    public NoteListVO list(NoteCondition condition) {
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        if (condition.getStatus() != null) {
            wrapper.eq("status", condition.getStatus());
        }
        if (StringUtils.isNotBlank(condition.getKeyword())) {
            wrapper.like("content", condition.getKeyword());
        }
        if (StringUtils.isNotBlank(condition.getAccId())) {
            wrapper.eq("acc_id", condition.getAccId());
        }
        wrapper.orderByDesc("update_time");
        List<Note> list = list(wrapper);
        if (CollectionUtils.isEmpty(list)) {
            return new NoteListVO();
        }
        return getNoteVoList(list);
    }

    @NotNull
    private NoteListVO getNoteVoList(List<Note> list) {
        List<NoteVO> voList = new ArrayList<>();
        HashMap<Integer, Integer> count = new HashMap<>(4);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Note note : list) {
                Integer status = note.getStatus();
                if (count.containsKey(status)) {
                    Integer recCount = count.get(status);
                    count.put(status, recCount + Symbol.ONE);
                } else {
                    count.put(status, Symbol.ONE);
                }
                NoteVO vo = NoteVO.builder()
                        .content(note.getContent())
                        .id(note.getId())
                        .status(status)
                        .notifyTime(note.getNotifyTime()).build();
                voList.add(vo);
            }
        }
        NoteListVO vo = new NoteListVO();
        vo.setList(voList);
        vo.setCount(count);
        return vo;
    }

    @Override
    public List<NoteVO> noteList(String accId) {
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        wrapper.eq("acc_id", accId).eq("status", NoteStatus.TO_BE_DONE.getValue());
        List<Note> list = list(wrapper);
        List<NoteVO> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Note note : list) {
                NoteVO vo = NoteVO.builder()
                        .content(note.getContent())
                        .id(note.getId())
                        .status(note.getStatus())
                        .notifyTime(note.getNotifyTime()).build();
                voList.add(vo);
            }
        }
        return voList;
    }

    @Override
    public int count(String accId) {
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        wrapper.eq("acc_id", accId).eq("status", NoteStatus.TO_BE_DONE.getValue());
        return count(wrapper);
    }
}
