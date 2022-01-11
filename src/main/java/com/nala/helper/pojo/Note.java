package com.nala.helper.pojo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 备忘录
 * </p>
 *
 * @author 彭术成
 * @since 2022-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("备忘录id")
    private int id;
    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 提醒时间
     */
    @ApiModelProperty("提醒时间")
    private Date notifyTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态 0 草稿 1 待执行  2 已完成 -1 删除
     */
    @ApiModelProperty("状态 0 草稿 1 待执行  2 已完成 -1 删除")
    private Integer status;

    @ApiModelProperty("os账号")
    private String accId;
}
