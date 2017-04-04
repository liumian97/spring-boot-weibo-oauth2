package win.liumian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;

/**
 * Created by liumian on 2017/4/4.
 */
@Controller("user")
public class UserController {


    @RequestMapping(value = "{id}")
    public String getUserInfo(@PathParam("id")int id){


        return "";
    }

}
