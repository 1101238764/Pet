package com.nala.helper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nala.helper.pojo.Poem;
import java.io.IOException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 彭术成
 * @since 2021-12-30
 */
public interface IPoemService extends IService<Poem> {

    /**
     * 数据爬取及存储
     *
     * @return 操作结果
     * @throws IOException io异常
     */
    boolean savePoem() throws IOException;

    /**
     * 每日一句
     *
     * @return 诗句
     */
    String get();
}
