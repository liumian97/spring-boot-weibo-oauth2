package win.liumian.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import win.liumian.conf.WeiboClientConf;
import win.liumian.exception.HttpException;
import win.liumian.util.HttpRequester;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liumian on 2017/4/3.
 */
@Controller
public class WeiboOAuth2Controller {

    private static Logger logger = LoggerFactory.getLogger(WeiboOAuth2Controller.class);

//    @Autowired
//    private WeiboConf weiboClient;

    @Autowired
    private WeiboClientConf weiboClient;

    @RequestMapping(value = "/login/oauth2/weibo", method = RequestMethod.GET)
    public ModelAndView requestCode(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("redirect:" + weiboClient.getUserAuthorizationUri());
        mav.addObject("client_id", weiboClient.getClientId());
        mav.addObject("redirect_uri", weiboClient.getRedirectUrl());
        mav.addObject("response_type", "code");

        return mav;


    }

    @RequestMapping(value = "/login/weibo")
    public String requestToken(@RequestParam("code") String code, HttpSession session) throws HttpException {

        logger.debug("code:{}", code);

        //处理参数

        JSONObject object = new JSONObject();
        object.put("code", code);
        object.put("client_id", weiboClient.getClientId());
        object.put("client_secret", weiboClient.getClientSecret());
        object.put("grant_type", "authorization_code");
        object.put("redirect_uri", weiboClient.getRedirectUrl());


        JSONObject jsonObject = HttpRequester.post(weiboClient.getAccessTokenUri(), object);
        saveTokenTo(session, jsonObject);
        System.out.println(jsonObject);

        return "redirect:/user/" + jsonObject.get("uid");
    }


    private void saveTokenTo(HttpSession session, JSONObject jsonObject) {

        session.setAttribute("access_token", jsonObject.get("access_token"));
        session.setAttribute("uid", jsonObject.get("uid"));
        session.setAttribute("expires_in", jsonObject.get("expires_in"));

    }


}
