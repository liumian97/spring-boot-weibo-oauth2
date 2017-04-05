package win.liumian.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * Created by liumian on 2017/4/3.
 */
@Component
@ConfigurationProperties(prefix = "weibo")
public class WeiboConf {

    @NestedConfigurationProperty
    private WeiboClientConf weiboClientConf = new WeiboClientConf();

    @NestedConfigurationProperty
    private WeiboResourceConf weiboResourceConf = new WeiboResourceConf();

    public WeiboClientConf getWeiboClientConf() {
        return weiboClientConf;
    }

    public void setWeiboClientConf(WeiboClientConf weiboClientConf) {
        this.weiboClientConf = weiboClientConf;
    }

    public WeiboResourceConf getWeiboResourceConf() {
        return weiboResourceConf;
    }

    public void setWeiboResourceConf(WeiboResourceConf weiboResourceConf) {
        this.weiboResourceConf = weiboResourceConf;
    }
}
