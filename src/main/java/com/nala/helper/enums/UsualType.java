package com.nala.helper.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * todo 描述当前类的功能
 *
 * @author 彭术成
 * @since 2022/1/6
 */
@Getter
@AllArgsConstructor
public enum UsualType {

    /**
     * 可用
     */
    USEFUL(1),
    /**
     * 失效
     */
    EXPIRE(-1);

    private int value;

}
