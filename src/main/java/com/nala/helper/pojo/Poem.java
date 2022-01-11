package com.nala.helper.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 彭术成
 * @since 2021-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Poem implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    /**
     * 内容
     */
    private String content;


}
