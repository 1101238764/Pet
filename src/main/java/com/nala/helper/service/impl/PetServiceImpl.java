package com.nala.helper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nala.helper.condition.PetCondition;
import com.nala.helper.constants.Symbol;
import com.nala.helper.mapper.PetMapper;
import com.nala.helper.pojo.Pet;
import com.nala.helper.service.IPetService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 个人信息表 服务实现类
 * </p>
 *
 * @author 彭术成
 * @since 2022-01-12
 */
@Service
public class PetServiceImpl extends ServiceImpl<PetMapper, Pet> implements IPetService {

    @Override
    public boolean updatePetName(PetCondition condition) {
        int count = count(condition.getPhone());
        if (count < 1) {
            Pet pet = new Pet();
            pet.setPetName(condition.getPetName());
            pet.setIsShow(Symbol.ONE);
            pet.setPhone(condition.getPhone());
            return save(pet);
        } else {
            UpdateWrapper<Pet> wrapper = new UpdateWrapper<>();
            wrapper.set("pet_name", condition.getPetName()).eq("phone", condition.getPhone());
            return update(wrapper);
        }
    }

    @Override
    public boolean switchShow(PetCondition condition) {
        int count = count(condition.getPhone());
        if (count < 1) {
            Pet pet = new Pet();
            pet.setPetName(condition.getPetName());
            pet.setIsShow(Symbol.ONE);
            pet.setPhone(condition.getPhone());
            return save(pet);
        } else {
            UpdateWrapper<Pet> wrapper = new UpdateWrapper<>();
            wrapper.set("is_show", condition.getIsShow()).eq("phone", condition.getPhone());
            return update(wrapper);
        }
    }

    private int count(String phone) {
        QueryWrapper<Pet> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        return count(wrapper);
    }
}
