package com.nala.helper.vo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * todo 描述当前类的功能
 *
 * @author 彭术成
 * @since 2021/12/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionVO implements Serializable {

    @ApiModelProperty("问题id")
    private String id;

    /**
     * 问题
     */
    @ApiModelProperty("问题")
    private String question;
}
