package com.nala.helper.vo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 答案
 *
 * @author 彭术成
 * @since 2021/12/22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerVO implements Serializable {

    @ApiModelProperty("答案id")
    private String id;

    /**
     * 问题id
     */
    @ApiModelProperty("问题id")
    private Integer questionId;

    /**
     * 答案内容
     */
    @ApiModelProperty("答案")
    private String answer;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date creatTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
