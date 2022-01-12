package com.nala.helper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nala.helper.condition.PetCondition;
import com.nala.helper.pojo.Pet;

/**
 * <p>
 * 个人信息表 服务类
 * </p>
 *
 * @author 彭术成
 * @since 2022-01-12
 */
public interface IPetService extends IService<Pet> {

    /**
     * 更新宠物名称
     *
     * @return 更新操作结果
     * @param condition 更新参数
     */
    boolean updatePetName(PetCondition condition);

    /**
     * 切换开关
     *
     * @return 操作结果
     * @param condition 更新参数
     */
    boolean switchShow(PetCondition condition);
}
