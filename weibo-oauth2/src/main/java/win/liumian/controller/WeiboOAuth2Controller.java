package win.liumian.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import win.liumian.conf.WeiboConf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liumian on 2017/4/3.
 */
@Controller
public class WeiboOAuth2Controller {

    private static Logger logger = LoggerFactory.getLogger(WeiboOAuth2Controller.class);

    @Autowired
    private WeiboConf weiboConf;

    @RequestMapping(value = "/login/oauth2/weibo",method = RequestMethod.GET)
    public ModelAndView requestCode(HttpServletRequest request){

        ModelAndView mav = new ModelAndView("redirect:"+weiboConf.getUserAuthorizationUri());
        mav.addObject("client_id",weiboConf.getClientId());
        mav.addObject("redirect_uri",weiboConf.getRedirectUrl());
        mav.addObject("response_type","code");

        return mav;


    }

    @RequestMapping(value = "/login/weibo")
    @ResponseBody
    public String requestToken(@RequestParam("code") String code, HttpSession session){

        logger.debug("code:{}",code);

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
                JSONObject jsonObject = JSONObject.parseObject(content);
                saveTokenTo(session,jsonObject);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        return content;
    }


    private void saveTokenTo(HttpSession session,JSONObject jsonObject){

        session.setAttribute("access_token",jsonObject.get("access_token"));
        session.setAttribute("uid",jsonObject.get("uid"));
        session.setAttribute("expires_in",jsonObject.get("expires_in"));

    }


}
