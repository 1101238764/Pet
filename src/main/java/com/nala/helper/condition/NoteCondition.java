package com.nala.helper.condition;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * 备忘录查询条件
 *
 * @author 彭术成
 * @since 2022/1/10
 */
@Data
public class NoteCondition implements Serializable {

    @ApiModelProperty("内容关键字")
    private String keyword;

    @ApiModelProperty("备忘状态(必传)")
    private Integer status;

    @ApiModelProperty("os 手机号（必传）")
    private String accId;
}
