package com.nala.helper.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 个人信息表
 * </p>
 *
 * @author 彭术成
 * @since 2022-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    /**
     * 精灵名称
     */
    private String petName;

    /**
     * os账号
     */
    private String phone;

    /**
     * 精灵开关 0 关闭  1开启
     */
    private Integer isShow;


}
