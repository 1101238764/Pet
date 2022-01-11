package com.nala.helper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nala.helper.pojo.HelloWord;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 彭术成
 * @since 2021-12-30
 */
public interface IHelloWordService extends IService<HelloWord> {

    String hello();

    boolean saveWords() throws Exception;
}
