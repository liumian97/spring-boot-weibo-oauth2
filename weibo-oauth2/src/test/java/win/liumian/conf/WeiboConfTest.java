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
    private WeiboClientConf weiboClient;

    @Autowired
    private WeiboResourceConf resourceConf;

    @Test
    public void test() {
        System.out.println(weiboClient.getAccessTokenUri());
        System.out.println(weiboClient.getClientAuthenticationScheme());
        System.out.println(weiboClient.getClientId());
        System.out.println(weiboClient.getClientSecret());
        System.out.println(weiboClient.getUserAuthorizationUri());
        System.out.println(resourceConf.getFriendsUrl());
    }


}
