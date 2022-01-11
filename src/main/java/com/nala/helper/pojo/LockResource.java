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
 * 资源控制
 * </p>
 *
 * @author 彭术成
 * @since 2022-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LockResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    /**
     * 锁名
     */
    private String name;

    /**
     * 失效时间戳 ms
     */
    private Date expire;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
