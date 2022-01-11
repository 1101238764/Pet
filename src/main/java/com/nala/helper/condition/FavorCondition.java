package com.nala.helper.condition;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
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
public class FavorCondition implements Serializable {


    @ApiModelProperty("收藏id")
    private Long id;

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
    @ApiModelProperty("收藏内容")
    private String content;

    /**
     * 备注名
     */
    @ApiModelProperty("备注名")
    private String remark;
}
