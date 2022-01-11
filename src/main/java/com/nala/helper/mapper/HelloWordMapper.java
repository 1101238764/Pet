package com.nala.helper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nala.helper.pojo.HelloWord;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 彭术成
 * @since 2021-12-30
 */
@Mapper
public interface HelloWordMapper extends BaseMapper<HelloWord> {

}
