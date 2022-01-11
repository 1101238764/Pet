package com.nala.helper.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpURL;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * http请求工具类
 *
 * @author 彭术成
 * @since 2021/12/22
 */
@Slf4j
public class HttpClientUtil {

    private static final String CONTENT_TYPE = "ContentType:";
    private static final String FORM_DATA_HEAD = "application/x-www-from-urlencoded";
    private static final String JSON_HEAD = "application/json";

    private static final MultiThreadedHttpConnectionManager CONNECTION_MANAGER = new MultiThreadedHttpConnectionManager();

    private HttpClientUtil() {
    }

    private static HttpClient getHttpClient() {
        HttpClient httpClient = new HttpClient();
        httpClient.setHttpConnectionManager(CONNECTION_MANAGER);
        return httpClient;
    }

    /**
     * 发送Post请求
     */
    public static String sendPostFormRequest(Map<String, String> params, Map<String, String> headers, String url) {
        try {
            HttpClient httpClient = getHttpClient();
            PostMethod method = new PostMethod(url);
            headers.forEach(method::setRequestHeader);
            method.setRequestHeader(CONTENT_TYPE, FORM_DATA_HEAD);
            List<NameValuePair> parametersBodyList = new ArrayList<>();
            params.forEach((k, v) -> {
                NameValuePair pair = new NameValuePair(k, v);
                parametersBodyList.add(pair);
            });
            NameValuePair[] pairs = new NameValuePair[parametersBodyList.size()];
            method.setRequestBody(parametersBodyList.toArray(pairs));
            httpClient.executeMethod(method);
            InputStream inputStream = method.getResponseBodyAsStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            byte[] bytes = new byte[1024 * 1024 * 3];
            int n;
            StringBuilder response = new StringBuilder();
            while ((n = bis.read(bytes)) != -1) {
                response.append(new String(bytes, 0, n));
            }
            return response.toString();
        } catch (IOException e) {
            log.error("post请求失败", e);
            return null;
        } finally {
            CONNECTION_MANAGER.closeIdleConnections(1000 * 5);
        }

    }

    /**
     * 发送Post请求
     */
    public static String sendPostJsonRequest(Map<String, String> params, Map<String, String> headers, String url) {
        try {
            HttpClient httpClient = getHttpClient();
            PostMethod method = new PostMethod(url);
            headers.forEach(method::setRequestHeader);
            method.setRequestEntity(
                    new StringRequestEntity(JSON.toJSONString(params), JSON_HEAD, Charsets.UTF_8.displayName()));
            httpClient.executeMethod(method);
            return method.getResponseBodyAsString();
        } catch (IOException e) {
            log.error("post请求失败", e);
            return null;
        } finally {
            CONNECTION_MANAGER.closeIdleConnections(1000 * 5);
        }

    }

    /**
     * 发送Get请求
     */
    public static String sendGetRequest(Map<String, String> params, Map<String, String> headers, String url) {
        try {
            HttpClient httpClient = getHttpClient();
            GetMethod method = new GetMethod();
            method.setURI(new HttpURL(url));
            headers.forEach(method::setRequestHeader);
            HttpClientParams clientParams = new HttpClientParams();
            clientParams.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            method.setParams(clientParams);
            params.forEach(clientParams::setParameter);
            httpClient.executeMethod(method);
            return method.getResponseBodyAsString();
        } catch (IOException e) {
            log.error("post请求失败", e);
            return null;
        } finally {
            CONNECTION_MANAGER.closeIdleConnections(1000 * 5);
        }
    }

    /**
     * 发送Get请求
     */
    public static HttpMethodBase sendGetRequest( String url) {
        try {
            HttpClient httpClient = getHttpClient();
            GetMethod method = new GetMethod(url);
            httpClient.executeMethod(method);
            return method;
        } catch (IOException e) {
            log.error("post请求失败", e);
            return null;
        } finally {
            CONNECTION_MANAGER.closeIdleConnections(1000 * 5);
        }
    }
}
