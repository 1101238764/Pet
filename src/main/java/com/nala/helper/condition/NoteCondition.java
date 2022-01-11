package com.nala.helper.condition;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * todo 描述当前类的功能
 *
 * @author 彭术成
 * @since 2022/1/10
 */
@Data
public class NoteCondition implements Serializable {

    @ApiModelProperty("内容关键字")
    private String keyword;

    @ApiModelProperty("备忘状态")
    private Integer status;

    @ApiModelProperty("os账号")
    private String accId;
}
