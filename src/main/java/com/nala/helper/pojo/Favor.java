package com.nala.helper.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收藏表
 * </p>
 *
 * @author 彭术成
 * @since 2021-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Favor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * os账号
     */
    private String accountId;

    /**
     * 内容类型
     */
    private String type;

    /**
     * 收藏内容
     */
    private String content;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 备注名
     */
    private String remark;

}
