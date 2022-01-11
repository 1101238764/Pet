package com.nala.helper.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应结果VO
 *
 * @author 彭术成
 * @since 2022/1/7
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    @ApiModelProperty("响应码")
    private String returnCode;

    @ApiModelProperty("响应描述")
    private String describe;

    @ApiModelProperty("响应体")
    private T model;

    @ApiModelProperty("响应结果（ture|false）")
    private Boolean result;
}
