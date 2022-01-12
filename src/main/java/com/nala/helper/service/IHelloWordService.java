package com.nala.helper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nala.helper.pojo.HelloWord;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 彭术成
 * @since 2021-12-30
 */
public interface IHelloWordService extends IService<HelloWord> {

    /**
     * 每日一句
     *
     * @return 句子
     */
    String hello();

    /**
     * 数据爬取
     *
     * @return 爬取操作结果
     * @throws Exception 异常
     */
    boolean saveWords() throws Exception;
}
