package com.nala.helper.vo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 下拉列表VO
 *
 * @author 彭术成
 * @since 2022/1/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DropdownVO implements Serializable {

    private String key;

    private String value;

}
