package com.nala.helper.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息类型
 *
 * @author 彭术成
 * @since 2022/1/6
 */
@Getter
@AllArgsConstructor
public enum MsgType {
    /**
     * 普通消息
     */
    MSG("msg"),
    /**
     * 提醒类消息
     */
    NOTE("note"),
    /**
     * 通知类消息
     */
    SYS("sys"),
    /**
     * 登录消息
     */
    LOGIN("login");

    private String key;

}
