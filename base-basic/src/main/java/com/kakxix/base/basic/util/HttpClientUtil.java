/*
 * Copyright 2018 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.kakxix.base.basic.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpClientUtil {

    private static PoolingHttpClientConnectionManager cm;

    private static CloseableHttpClient                defaultClient;

    public static final String                        ENCODE                          = "UTF-8";

    public static final String                        CONTENT_TYPE_JSON               = "application/json";

    public static final String                        CONTENT_TYPE_FORM               = "application/x-www-form-urlencoded";

    private static final int                          maxTotal                        = 400;
    private static final int                          defaultMaxPerRoute              = 100;

    private static final int                          defaultSocketTimeout            = 12000;
    private static final int                          defaultConnectTimeout           = 5000;
    private static final int                          defaultConnectionRequestTimeout = 1000;

    static {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", createSSL()).register("http", PlainConnectionSocketFactory.getSocketFactory())
                .build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        // 将最大连接数
        cm.setMaxTotal(maxTotal);
        // 每个路由基础的连接
        cm.setDefaultMaxPerRoute(defaultMaxPerRoute);
        // 默认设置
        RequestConfig requestConfig = RequestConfig.custom()

                .setSocketTimeout(defaultSocketTimeout).setConnectTimeout(defaultConnectTimeout)
                .setConnectionRequestTimeout(defaultConnectionRequestTimeout).build();
        defaultClient = (HttpClients.custom().setDefaultRequestConfig(requestConfig).setConnectionManager(cm).build());

    }

    public static String doGet(String url, Map<String, String> param, int timeout) throws ParseException, IOException {
        String uri = url;

        uri += convertQueryString(param);

        HttpRequestBase rq = new HttpGet(uri);

        initTimeout(rq, timeout);

        return getResult(rq);
    }


    /**
     * 获取远程网络图片信息
     *
     * @return
     */
    public static CloseableHttpResponse getRemoteBufferedImage(String url1, String jsonStr, int timeout) {
        URL url = null;
        InputStream is = null;
        BufferedImage bufferedImage = null;
        byte[] bytes = null;
        try {
            HttpPost rq = new HttpPost(url1);
            initTimeout(rq, timeout);
//            rq.addHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_JSON);
            StringEntity se = new StringEntity(jsonStr, ENCODE);
//            se.setContentType(CONTENT_TYPE_JSON);
//            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_JSON));
            rq.setEntity(se);
            CloseableHttpClient httpClient = getHttpClient();
            CloseableHttpResponse response = null;
            response = httpClient.execute(rq);

            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            log.error("imageURL: " + url1 + ",无效!");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("imageURL: " + url1 + ",读取失败!");
            return null;
        }
    }


    private static String convertQueryString(Map<String, String> params) throws UnsupportedEncodingException {
        if (params == null) {
            return "";
        }
        String query = "?";
        for (String name : params.keySet()) {
            query = query + name + "=" + URLEncoder.encode(params.get(name), ENCODE) + "&";
        }
        query = query.substring(0, query.length() - 1);
        return query;
    }

    public static String doPostJson(String url, String jsonStr, int timeout) throws ParseException, IOException {
        HttpPost rq = new HttpPost(url);
        initTimeout(rq, timeout);
        rq.addHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_JSON);
        StringEntity se = new StringEntity(jsonStr, ENCODE);
        se.setContentType(CONTENT_TYPE_JSON);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_JSON));
        rq.setEntity(se);

        return getResult(rq);
    }

    public static String doPostForm(String url, Map<String, String> param, int timeout)
            throws ParseException, IOException {

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (null != param) {
            for (String name : param.keySet()) {
                String value = param.get(name);
                if (value != null) {
                    nvps.add(new BasicNameValuePair(name, value));
                }
            }
        }
        return doPostForm(url, nvps, timeout);
    }

    public static String doPostForm(String url, JSONObject param, int timeout) throws ParseException, IOException {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (null != param) {
            for (String name : param.keySet()) {
                String value = param.get(name).toString();
                if (value != null) {
                    nvps.add(new BasicNameValuePair(name, value));
                }
            }
        }

        return doPostForm(url, nvps, timeout);
    }

    private static String doPostForm(String url, List<NameValuePair> nvps, int timeout)
            throws ParseException, IOException {
        HttpPost rq = new HttpPost(url);
        initTimeout(rq, timeout);
        rq.addHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_FORM);

        StringEntity se = null;

        se = new UrlEncodedFormEntity(nvps, ENCODE);
        se.setContentType(CONTENT_TYPE_FORM);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_FORM));
        rq.setEntity(se);

        return getResult(rq);
    }

    /**
     * HTTP方式调用HSF接口
     * 
     * @param url
     * @param paramTypes
     * @param paramValues
     * @param timeout 单位毫秒
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static String doPostHsf(String url, Object[] paramTypes, Object[] paramValues, int timeout)
            throws ParseException, IOException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ArgsTypes", JSONObject.toJSONString(paramTypes));
        map.put("ArgsObjects", JSONObject.toJSONString(paramValues));
        return doPostForm(url, map, timeout);
    }

    public static String buildHsfUrl(String slbUrl, String interfaceName, String hsfVersion, String methodName) {
        String url = "http://" + slbUrl + "/" + interfaceName + ":" + hsfVersion + "/" + methodName;
        return url;
    }

    private static void initTimeout(HttpRequestBase rq, int timeout) {
        if (timeout > 0) {
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout)
                    .build();//设置请求和传输超时时间
            rq.setConfig(requestConfig);
        }
    }

    public static CloseableHttpClient getHttpClient() {
        return defaultClient;
    }

    public static SSLConnectionSocketFactory createSSL() {
        SSLContext sslContext = null;
        try {

            //sslContext = new SSLContextBuilder().loadTrustMaterial(KeyUtil.getJks()).build();
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
        } catch (Exception e) {
            log.info("createSSL is error ", e);
        }
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
        return sslConnectionSocketFactory;
    }

    public CloseableHttpClient getDefaultClient() {
        return defaultClient;
    }

    private static String getResult(HttpRequestBase request) throws ParseException, IOException {
        CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            response = httpClient.execute(request);
            entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, ENCODE);
                return result;
            }
        } finally {
            try {
                if (entity != null) {
                    EntityUtils.consume(entity);
                }
                if (response != null) {

                    response.close();
                }
            } catch (IOException e) {
                log.info("getDefaultClient is error ", e);
            }
        }
        return "";
    }

}
