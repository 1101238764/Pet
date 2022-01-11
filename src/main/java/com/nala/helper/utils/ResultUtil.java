package com.nala.helper.utils;

import com.nala.helper.vo.ResultVO;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

/**
 * 响应工具类
 *
 * @author 彭术成
 * @since 2022/1/7
 */
public class ResultUtil {

    private ResultUtil() {

    }

    /**
     * 成功响应
     *
     * @param tClass 响应体内容
     * @param <T>    响应体类型
     * @return 响应成功结果
     */
    @NotNull
    public static <T> ResultVO<T> success(T tClass) {
        ResultVO<T> vo = new ResultVO<>();
        vo.setDescribe(HttpStatus.OK.getReasonPhrase());
        vo.setReturnCode(String.valueOf(HttpStatus.OK.value()));
        vo.setResult(true);
        vo.setModel(tClass);
        return vo;
    }

    /**
     * 失败响应
     *
     * @param <T> 响应体类型
     * @return 响应失败结果
     */
    @NotNull
    public static <T> ResultVO<T> failed() {
        ResultVO<T> vo = new ResultVO<>();
        vo.setReturnCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()));
        vo.setResult(false);
        vo.setDescribe(HttpStatus.EXPECTATION_FAILED.getReasonPhrase());
        return vo;
    }

    /**
     * boolean 响应结果
     *
     * @param result boolean响应结果
     * @return 响应成功or失败的结果
     */
    @NotNull
    public static ResultVO<Boolean> booleanResult(boolean result) {
        ResultVO<Boolean> vo = new ResultVO<>();
        if (result) {
            vo.setDescribe(HttpStatus.OK.getReasonPhrase());
            vo.setReturnCode(String.valueOf(HttpStatus.OK.value()));
        } else {
            vo.setDescribe(HttpStatus.EXPECTATION_FAILED.getReasonPhrase());
            vo.setReturnCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()));
        }
        vo.setResult(result);
        vo.setModel(result);
        return vo;
    }

}
