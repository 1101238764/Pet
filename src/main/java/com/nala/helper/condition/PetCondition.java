package com.nala.helper.condition;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * 宠物信息条件
 *
 * @author 彭术成
 * @since 2022/1/12
 */
@Data
public class PetCondition implements Serializable {

    @ApiModelProperty("宠物名")
    private String petName;

    @ApiModelProperty("开关 0 关闭 1展示")
    private int isShow;

    @ApiModelProperty("os 手机号")
    private String phone;
}
