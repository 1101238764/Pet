package com.nala.helper.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 彭术成
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 问题id
     */
    private Integer questionId;

    /**
     * 答案内容
     */
    private String answer;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
