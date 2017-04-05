package win.liumian.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import win.liumian.exception.HttpException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by liumian on 2017/4/4.
 */
public class HttpRequester {

    private static Logger logger = LoggerFactory.getLogger(HttpRequester.class);

    public static JSONObject post(String url, JSONObject jsonParas) throws HttpException {

        HttpPost httpPost = new HttpPost(url);

        //设置参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator iterator = jsonParas.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }

        try {
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
                httpPost.setEntity(entity);
            }
            return doExecute(httpPost);

        } catch (IOException e) {
            e.printStackTrace();
            throw new HttpException("http post 出错，");
        }
    }


    public static JSONObject get(String url, JSONObject jsonParas) throws HttpException {

        StringBuilder sb = new StringBuilder(url + "?");
        Iterator<Map.Entry<String, Object>> iterator = jsonParas.entrySet().iterator();
        Map.Entry entry = null;
        if (iterator.hasNext()) {
            entry = iterator.next();
            sb.append(entry.getKey() + "=" + entry.getValue());
        }
        while (iterator.hasNext()) {
            entry = iterator.next();
            sb.append("&" + entry.getKey() + "=" + entry.getValue());
        }

        HttpGet httpGet = new HttpGet(sb.toString());

        try {
            return doExecute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
            throw new HttpException("http post 出错，");
        }
    }




    private static JSONObject doExecute(HttpRequestBase requestBase) throws IOException {

        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(requestBase);
        JSONObject result = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
        logger.info("uri:{}" + requestBase.getRequestLine().getUri()
                + ",params:{}" + requestBase.getParams().toString()
                + ",result:{}" + result);
        return JSON.parseObject(result.toJSONString());
    }

}
