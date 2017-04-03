package win.liumian.oauth2;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import win.liumian.conf.WeiboConf;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liumian on 2017/4/3.
 */
@Controller
public class WeiboOAuth2 {


    @Autowired
    private WeiboConf weiboConf;

    @RequestMapping(value = "/login/weibo",method = RequestMethod.GET)
    public String requestCode(HttpServletRequest request){

        return "redirect:/"+weiboConf.getUserAuthorizationUri()
                +"?client_id="+weiboConf.getClientId()
                +"&redirect_uri="+request.getRequestURL()
                +"&response_type=code";


    }

    @RequestMapping(value = "/login/weibo",params = "code",consumes = "text/json")
    @ResponseBody
    public String requestToken(@RequestPart("code") String code){

        HttpClient client = HttpClients.createDefault();

        HttpPost post = new HttpPost(weiboConf.getAccessTokenUri());

        //处理参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("code",code));
        nvps.add(new BasicNameValuePair("client_id",weiboConf.getClientId()));
        nvps.add(new BasicNameValuePair("client_secret",weiboConf.getClientSecret()));
        nvps.add(new BasicNameValuePair("grant_type","authorization_code"));
        nvps.add(new BasicNameValuePair("redirect_uri",weiboConf.getRedirectUrl()));


        //结果
        HttpResponse response = null;
        String content="";
        try {
            //提交的参数
            UrlEncodedFormEntity uefEntity  = new UrlEncodedFormEntity(nvps, "UTF-8");
            //将参数给post方法
            post.setEntity(uefEntity);
            //执行post方法
            response = client.execute(post);
            if(response.getStatusLine().getStatusCode()==200){
                content = EntityUtils.toString(response.getEntity(),"utf-8");
                System.out.println(content);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }


}
