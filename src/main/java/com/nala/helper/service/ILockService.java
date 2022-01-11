package com.nala.helper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nala.helper.pojo.LockResource;

/**
 * <p>
 * 资源控制 服务类
 * </p>
 *
 * @author 彭术成
 * @since 2022-01-06
 */
public interface ILockService extends IService<LockResource> {


    /**
     * 加锁
     *
     * @param name   锁名
     * @param expire 到期时间
     * @return 操作结果
     */
    boolean lock(String name, long expire);

    /**
     * 删锁
     *
     * @param name 锁名
     */
    void remove(String name);


}
