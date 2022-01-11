package com.nala.helper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nala.helper.condition.NoteCondition;
import com.nala.helper.pojo.Note;
import com.nala.helper.vo.NoteListVO;
import com.nala.helper.vo.NoteVO;
import java.util.List;

/**
 * <p>
 * 备忘录 服务类
 * </p>
 *
 * @author 彭术成
 * @since 2022-01-06
 */
public interface INoteService extends IService<Note> {


    /**
     * 新增备忘录
     *
     * @param note 新增对象
     * @return 操作结果
     */
    boolean add(Note note);

    /**
     * 更新备忘
     *
     * @param note 备忘对象
     * @return 操作结果
     */
    boolean edit(Note note);

    /**
     * 备忘录列表查询
     *
     * @param condition 查询条件
     * @return 备忘录列表
     */
    NoteListVO list(NoteCondition condition);

    /**
     * 查询待处理备忘
     *
     * @param accId 账号
     * @return 备忘列表
     */
    List<NoteVO> noteList(String accId);

    /**
     * 代办数量提醒
     *
     * @param accId 账号
     * @return 代办数
     */
    int count(String accId);
}
