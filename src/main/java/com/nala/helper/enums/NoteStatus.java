package com.nala.helper.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 备忘录状态
 *
 * @author 彭术成
 * @since 2022/1/7
 */
@Getter
@AllArgsConstructor
public enum NoteStatus {

    /**
     * 草稿
     */
    GRAFTS("0", "草稿"),
    /**
     * 代办
     */
    TO_BE_DONE("1", "代办"),
    /**
     * 已完成
     */
    FINISH("2", "已完成"),
    /**
     * 已删除
     */
    DEL("-1", "已删除");

    private String value;
    private String desc;
}
