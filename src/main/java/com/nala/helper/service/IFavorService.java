package com.nala.helper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nala.helper.condition.FavorCondition;
import com.nala.helper.pojo.Favor;
import com.nala.helper.vo.FavorVO;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 * @author 彭术成
 * @since 2021-12-27
 */
public interface IFavorService extends IService<Favor> {

    /**
     * 收藏
     *
     * @param condition 新增参数
     * @return 操作结果
     */
    boolean collect(FavorCondition condition);

    /**
     * 列表查询
     *
     * @param condition 查询条件
     * @return 收藏列表
     */
    List<FavorVO> list(FavorCondition condition);

    /**
     * 收藏编辑
     *
     * @param condition 编辑字段
     * @return 操作结果
     */
    boolean edit(FavorCondition condition);

    /**
     * 分类列表查询
     *
     * @param condition 查询条件
     * @return 收藏列表
     */
    Map<String, List<FavorVO>> listByType(FavorCondition condition);
}
