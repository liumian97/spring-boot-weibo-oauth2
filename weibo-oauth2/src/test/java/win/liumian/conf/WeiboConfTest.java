package win.liumian.conf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liumian on 2017/4/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WeiboConfTest {

    @Autowired
    private WeiboConf weiboConf;


    @Test
    public void test(){
        System.out.println(weiboConf.getAccessTokenUri());
        System.out.println(weiboConf.getClientAuthenticationScheme());
        System.out.println(weiboConf.getClientId());
        System.out.println(weiboConf.getClientSecret());
        System.out.println(weiboConf.getUserAuthorizationUri());
    }


}
