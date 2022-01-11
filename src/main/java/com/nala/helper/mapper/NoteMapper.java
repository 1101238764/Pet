package com.nala.helper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nala.helper.pojo.Note;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 备忘录 Mapper 接口
 * </p>
 *
 * @author 彭术成
 * @since 2022-01-06
 */
@Mapper
public interface NoteMapper extends BaseMapper<Note> {

}
