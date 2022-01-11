package com.nala.helper.pojo;

import com.alibaba.fastjson.JSON;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 图灵聊天机器人返回对象
 *
 * @author 彭术成
 * @since 2021/12/23
 */
@Data
public class TuringTestResponse {

    private String type;

    private String code;

    private DataInfo data;


    @Data
    public static class DataInfo implements Serializable {

        private String globalId;

        private String intent;

        private List<Result> results;
    }

    @Data
    public static class Result implements Serializable {

        private String groupType;

        private String resultType;

        /**
         * JSON解析后获取 text即可
         */
        private String values;

        public String getValues() {
            return JSON.parseObject(values).getString("text");
        }
    }
}
