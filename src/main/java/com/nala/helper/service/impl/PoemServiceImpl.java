package com.nala.helper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nala.helper.mapper.PoemMapper;
import com.nala.helper.pojo.Poem;
import com.nala.helper.service.IPoemService;
import com.nala.helper.utils.HttpClientUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethodBase;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 彭术成
 * @since 2021-12-30
 */
@Service
public class PoemServiceImpl extends ServiceImpl<PoemMapper, Poem> implements IPoemService {

    private static int sId = 0;

    @Override
    public boolean savePoem() throws IOException {

        List<Poem> list = new ArrayList<>();

        String url;
        int i = 1;
        while (i < 11) {
            url = "https://so.gushiwen.cn/shiwens/default.aspx?page=" + i + "&tstr=&astr=&cstr=&xstr=%e8%af%97";
            i++;
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
                if (!content.contains("id=\"leftZhankai\"")) {
                    i++;
                    continue;
                }
                String s = content.split("id=\"leftZhankai\"")[1].split("<form")[0];
                String[] split = s.split("class=\"contson\"");
                for (String s1 : split) {
                    if (!s1.equals(split[0])) {
                        String s2 = s1.split("\">")[1].split("</div>")[0].trim();
                        Poem poem = new Poem();
                        poem.setContent(s2);
                        list.add(poem);
                    }
                }
            }
        }
        saveBatch(list);
        return true;
    }

    @Override
    public String get() {
        int count = count();
        while (true) {
            int id = new Random().nextInt(count);
            Poem poem = getById(id);
            if (sId != id && poem != null) {
                sId = id;
                return poem.getContent();
            }
        }
    }
}
