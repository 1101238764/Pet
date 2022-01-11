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
 * @since 2021/12/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavorVO implements Serializable {

    @ApiModelProperty("收藏id")
    private String id;

    /**
     * os账号
     */
    @ApiModelProperty("os账号")
    private String accountId;

    /**
     * 内容类型
     */
    @ApiModelProperty("内容类型")
    private String type;

    /**
     * 收藏内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 备注名
     */
    @ApiModelProperty("备注名")
    private String remark;

}
