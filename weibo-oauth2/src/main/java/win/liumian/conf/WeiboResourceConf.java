package win.liumian.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by liumian on 2017/4/4.
 */
@Component
@ConfigurationProperties(prefix = "weibo.resource")
public class WeiboResourceConf {
    private String friendsUrl;

    public String getFriendsUrl() {
        return friendsUrl;
    }

    public void setFriendsUrl(String friendsUrl) {
        this.friendsUrl = friendsUrl;
    }
}
