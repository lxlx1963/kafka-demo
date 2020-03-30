package com.polarico.kafka.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author dxy
 */
public class HttpClientUtils {
    private static CloseableHttpClient httpclient = HttpClients.createDefault();

    /**
     * post请求
     *
     * @param uri          地址
     * @param parameterMap 参数Map
     * @throws IOException
     */
    public static void doPost(String uri, Map<String, String> parameterMap) throws IOException {
        Assert.notNull(uri, "uri is null");

        HttpPost httpPost = new HttpPost(uri);
        if (null != parameterMap && parameterMap.size() > 0) {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                nameValuePairList.add(new BasicNameValuePair(key, value));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList));
        }

        try (CloseableHttpResponse response = httpclient.execute(httpPost);) {
            HttpEntity entity = response.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
        }
    }
}
