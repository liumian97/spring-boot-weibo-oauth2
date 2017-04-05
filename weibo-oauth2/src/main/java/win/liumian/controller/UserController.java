package win.liumian.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.liumian.conf.WeiboClientConf;
import win.liumian.conf.WeiboResourceConf;
import win.liumian.exception.HttpException;
import win.liumian.util.HttpRequester;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by liumian on 2017/4/4.
 */
@RestController
public class UserController {

//    @Autowired
//    private WeiboConf weiboConf;


    @Autowired
    private WeiboResourceConf resourceConf;

    @Autowired
    private WeiboClientConf clientConf;



    @RequestMapping(value = "user/{id}")
    public String getUserInfo(@PathVariable("id")String id, HttpSession session) throws HttpException {

        String token = (String) session.getAttribute("access_token");
        if(token == null){
            //token过期，请重新授权
        }
        Map<String,Object> paras = new HashMap<>();
        paras.put("access_token",token);
        paras.put("uid",id);
        paras.put("client_id",clientConf.getClientId());

        JSONObject result = HttpRequester.get(resourceConf.getFriendsUrl(), new JSONObject(paras));

        return result.toJSONString();
    }

}
