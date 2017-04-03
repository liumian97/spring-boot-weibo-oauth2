package win.liumian.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by liumian on 2017/4/3.
 */
@Component
@ConfigurationProperties(prefix = "weibo.client")
public class WeiboConf {

    private String clientId;
    private String clientSecret;

    private String accessTokenUri;

    private String userAuthorizationUri;

    private String clientAuthenticationScheme;

    private String redirectUrl;

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    //    private String


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAccessTokenUri() {
        return accessTokenUri;
    }

    public void setAccessTokenUri(String accessTokenUri) {
        this.accessTokenUri = accessTokenUri;
    }

    public String getUserAuthorizationUri() {
        return userAuthorizationUri;
    }

    public void setUserAuthorizationUri(String userAuthorizationUri) {
        this.userAuthorizationUri = userAuthorizationUri;
    }

    public String getClientAuthenticationScheme() {
        return clientAuthenticationScheme;
    }

    public void setClientAuthenticationScheme(String clientAuthenticationScheme) {
        this.clientAuthenticationScheme = clientAuthenticationScheme;
    }
}
