package com.nala.helper.api.out.impl;

import com.alibaba.fastjson.JSON;
import com.nala.helper.api.out.TuringOsApi;
import com.nala.helper.constants.Symbol;
import com.nala.helper.pojo.TuringTestResponse;
import com.nala.helper.utils.HttpClientUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 图灵api实现类
 *
 * @author 彭术成
 * @since 2021/12/23
 */
@Service
@Slf4j
public class TuringOsApiImpl implements TuringOsApi {

    @Override
    public String testChat(String content) {
        String url = "http://biz.turingos.cn/apirobot/dialog/homepage/chat";
        Map<String, String> params = new HashMap<>(2);
        params.put("deviceId", UUID.randomUUID().toString().replaceAll(Symbol.SHORT_LINE, ""));
        params.put("question", content);
        Map<String, String> header = new HashMap<>(2);
        String response = HttpClientUtil.sendPostFormRequest(params, header, url);
        System.out.println(response);
        try {
            TuringTestResponse turingTestResponse = JSON.parseObject(response, TuringTestResponse.class);
            if (turingTestResponse != null) {
                return turingTestResponse.getData().getResults().get(0).getValues();
            }
        } catch (Exception e) {
            log.error("解析异常", e);
        }
        return "问题难倒了瓜娃子。";
    }
}
