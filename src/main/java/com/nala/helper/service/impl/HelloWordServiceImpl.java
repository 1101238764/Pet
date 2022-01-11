package com.nala.helper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nala.helper.mapper.HelloWordMapper;
import com.nala.helper.pojo.HelloWord;
import com.nala.helper.service.IHelloWordService;
import com.nala.helper.utils.HttpClientUtil;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author 彭术成
 * @since 2021-12-30
 */
@Service
public class HelloWordServiceImpl extends ServiceImpl<HelloWordMapper, HelloWord> implements IHelloWordService {

    private static int pre = 0;

    @Override
    public String hello() {
        int count = count();
        while (true) {
            int id = new Random().nextInt(count);
            HelloWord word = getById(id);
            if (pre != id && word != null) {
                pre = id;
                return word.getContent();
            }
        }
    }

    @Override
    public boolean saveWords() throws Exception {

        List<HelloWord> list = new ArrayList<>();

        String url = "";
        int n = 200;
        int i = 1;
        while (i < n) {
            if (StringUtils.isBlank(url)) {
                url = "https://www.ruiwen.com/weimeijuzi/4456091.html";
            }
            HttpMethodBase base = HttpClientUtil.sendGetRequest(url);
            InputStream in = Objects.requireNonNull(base).getResponseBodyAsStream();
            Header contentType = base.getResponseHeader("content-Type");
            String charset = contentType.getValue().split("charset=")[1].split("\"")[0];

            if (in == null) {
                return false;
            }
            try (BufferedReader bis = new BufferedReader(new InputStreamReader(in, charset))) {
                StringBuilder sb = new StringBuilder();
                char[] bytes = new char[1024];
                int len;
                while ((len = bis.read(bytes)) != -1) {
                    sb.append(bytes, 0, len);
                }
                // 获取内容
                String content = sb.toString();
                String[] split = content.split("detail_article article-content");
                String s = "";
                try {
                    s = split[1];
                } catch (Exception e) {
                    i++;
                    continue;
                }

                for (String s1 : s.split("<p>")) {
                    if (StringUtils.isNotBlank(s1.trim())) {
                        String s2 = s1.replaceAll(String.valueOf((char) 12288), "").trim().substring(0, 1);
                        try {
                            Integer.parseInt(s2);
                        } catch (NumberFormatException e) {
                            i++;
                            continue;
                        }
                        String convert = s1.split("</p>")[0]
                                .replaceAll(String.valueOf((char) 12288), "").trim();
                        String word = "";
                        if (convert.contains("、")) {
                            word = convert.split("、")[1];
                        }
                        if (convert.contains(" ")) {
                            word = convert.split(" ")[1];
                        }

                        HelloWord helloWord = new HelloWord();
                        helloWord.setContent(word);
                        list.add(helloWord);
                    }
                }
                i++;
                // 获取下一篇
                if (content.contains("post-next fr")) {
                    String a = content.split("post-next fr")[1].split("</a>")[0];
                    url = a.split("href='")[1].split("'>")[0];
                } else {
                    break;
                }
            }
            System.out.println(i);
        }
        saveBatch(list);

        return true;
    }
}
