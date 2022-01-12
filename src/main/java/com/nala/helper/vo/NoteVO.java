package com.nala.helper.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * todo 描述当前类的功能
 *
 * @author 彭术成
 * @since 2022/1/10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteVO implements Serializable {

    @ApiModelProperty("备忘录id")
    private Integer id;
    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 提醒时间
     */
    @ApiModelProperty("提醒时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date notifyTime;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 状态 0 草稿 1 待执行  2 已完成 -1 删除
     */
    @ApiModelProperty("状态 0 草稿 1 待执行  2 已完成 -1 删除")
    private Integer status;

}
