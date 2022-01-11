package com.nala.helper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nala.helper.mapper.LockMapper;
import com.nala.helper.pojo.LockResource;
import com.nala.helper.service.ILockService;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源控制 服务实现类
 * </p>
 *
 * @author 彭术成
 * @since 2022-01-06
 */
@Service
public class LockServiceImpl extends ServiceImpl<LockMapper, LockResource> implements ILockService {

    @Override
    public boolean lock(String name, long expire) {
        QueryWrapper<LockResource> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        LockResource lockResource = getOne(wrapper);
        if (lockResource.getExpire().before(new Date())) {
            // 删除过期锁
            removeById(lockResource.getId());

            // 新增锁
            LockResource lockreSource = LockResource.builder()
                    .name(name)
                    .createTime(new Date())
                    .updateTime(new Date())
                    .expire(new Date(expire)).build();
            return save(lockreSource);

        }
        return false;
    }

    @Override
    public void remove(String name) {
        UpdateWrapper<LockResource> wrapper = new UpdateWrapper<>();
        wrapper.eq("name", name);
        remove(wrapper);
    }
}
