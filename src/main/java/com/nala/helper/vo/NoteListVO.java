package com.nala.helper.vo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * todo 描述当前类的功能
 *
 * @author 彭术成
 * @since 2022/1/10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteListVO implements Serializable {

    @ApiModelProperty("备忘列表")
    private List<NoteVO> list;


    @ApiModelProperty("key：状态 ,value: 数量")
    private HashMap<Integer, Integer> count;
}
