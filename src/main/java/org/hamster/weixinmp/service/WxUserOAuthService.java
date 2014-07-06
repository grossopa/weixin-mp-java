/**
 * 
 */
package org.hamster.weixinmp.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.user.WxUserEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.model.oauth.WxOAuthTokenJson;
import org.hamster.weixinmp.model.user.WxUserInfoJson;
import org.hamster.weixinmp.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.google.api.client.util.Maps;

/**
 * 如果用户在微信中（Web微信除外）访问公众号的第三方网页，公众号开发者可以通过此接口获取当前用户基本信息（包括昵称、性别、城市、国家）。利用用户信息，
 * 可以实现体验优化、用户来源统计、帐号绑定、用户身份鉴权等功能。请注意，“获取用户基本信息接口是在用户和公众号产生消息交互时
 * ，才能根据用户OpenID获取用户基本信息，而网页授权的方式获取用户基本信息，则无需消息交互，只是用户进入到公众号的网页，就可弹出请求用户授权的界面， 用户授权后，就可获得其基本信息（此过程甚至不需要用户已经关注公众号。）”<br>
 * 
 * 微信OAuth2.0授权登录让微信用户使用微信身份安全登录第三方应用或网站，在微信用户授权登录已接入微信OAuth2.0的第三方应用后，第三方可以获取到用户的接口调用凭证（access_token），
 * 通过access_token可以进行微信开放平台授权关系接口调用，从而可实现获取微信用户基本开放信息和帮助用户实现基础开放功能等。<br>
 * 
 * 在微信公众号请求用户网页授权之前，开发者需要先到公众平台网站的我的服务页中配置授权回调域名。请注意，这里填写的域名不要加http://<br>
 * 
 * @author <a href="mailto:grossopaforever@gmail.com">Jack Yin</a>
 * @version Jul 6, 2014 11:12:55 AM
 * 
 */
@Service
public class WxUserOAuthService {
    @Autowired
    WxConfig config;

    @Autowired
    WxStorageService storageService;

    /**
     * <p>
     * <b>第一步：用户同意授权，获取code</b>
     * </p>
     * 
     * 在确保微信公众账号拥有授权作用域（scope参数）的权限的前提下（服务号获得高级接口后，默认带有scope参数中的snsapi_base和snsapi_userinfo），引导关注者打开该页面.<br>
     * 
     * <br>
     * <b>用户同意授权后</b><br>
     * 如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE。若用户禁止授权，则重定向后不会带上code参数，仅会带上state参数redirect_uri?state=STATE<br>
     * 
     * @param scope
     * @param redirectUri
     * @param state
     * @throws IOException
     */
    public void getCodeUrl(Scope scope, String redirectUri, String state) throws IOException {
        try {
            URIBuilder builder = new URIBuilder(config.getUserOAuthUrl());
            builder.addParameter("appid", config.getAppid()).addParameter("redirect_uri", redirectUri)
                    .addParameter("scope", scope.toString()).addParameter("response_type", "code");
            if (StringUtils.isNotEmpty(state)) {
                builder.addParameter("state", state);
            }
            builder.setFragment("wechat_redirect");
        } catch (URISyntaxException e) {
            // never happens
            e.printStackTrace();
        }
    }

    /**
     * <p>
     * <b>第二步：通过code换取网页授权access_token</b>
     * </p>
     * 
     * 首先请注意，这里通过code换取的网页授权access_token,与基础支持中的access_token不同。公众号可通过下述接口来获取网页授权access_token。如果网页授权的作用域为snsapi_base，
     * 则本步骤中获取到网页授权access_token的同时，也获取到了openid，snsapi_base式的网页授权流程即到此为止。<br>
     * 
     * 正确时返回的JSON数据包如下：<br>
     * <code>
     * {<br>
     * &nbsp;&nbsp;"access_token":"ACCESS_TOKEN",<br>
     * &nbsp;&nbsp;"expires_in":7200,<br>
     * &nbsp;&nbsp;"refresh_token":"REFRESH_TOKEN",<br>
     * &nbsp;&nbsp;"openid":"OPENID",<br>
     * &nbsp;&nbsp;"scope":"SCOPE"<br>
     * }<br>
     * </code>
     * 
     * @param code
     * @throws WxException
     */
    public WxUserEntity fetchAccessTokenByCode(String code) throws WxException {
        Map<String, String> params = Maps.newHashMap();
        params.put("appid", config.getAppid());
        params.put("appsecret", config.getAppsecret());
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        WxOAuthTokenJson json = WxUtil.sendRequest(config.getUserOAuthAccessTokenUrl(), HttpMethod.GET, params, null,
                WxOAuthTokenJson.class);
        return storageService.saveOrUpdateUser(json);
    }

    /**
     * <p>
     * <b>第三步：刷新access_token（如果需要）</b>
     * </p>
     * 
     * 由于access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新，refresh_token拥有较长的有效期（7天、30天、60天、90天），
     * 当refresh_token失效的后，需要用户重新授权。<br>
     * 
     * @param refreshToken 填写通过access_token获取到的refresh_token参数
     * @return
     * @throws WxException
     */
    public WxUserEntity refreshToken(String refreshToken) throws WxException {
        Map<String, String> params = Maps.newHashMap();
        params.put("appid", config.getAppid());
        params.put("refresh_token", refreshToken);
        params.put("grant_type", "refresh_token");
        WxOAuthTokenJson json = WxUtil.sendRequest(config.getUserOAuthRefreshTokenUrl(), HttpMethod.GET, params, null,
                WxOAuthTokenJson.class);

        return storageService.saveOrUpdateUser(json);
    }

    /**
     * <p><b>第四步：拉取用户信息(需scope为 snsapi_userinfo)</b></p>
     * 
     * 如果网页授权作用域为snsapi_userinfo，则此时开发者可以通过access_token和openid拉取用户信息了。<br>
     * 
     * @param access_token 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * @param openid 用户的唯一标识
     * @param lang 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     * @return
     * @throws WxException
     */
    public WxUserEntity getUserInfo(String accessToken, String openid, String lang) throws WxException {
        Map<String, String> params = Maps.newHashMap();
        params.put("appid", config.getAppid());
        params.put("accessToken", accessToken);
        params.put("openid", openid);
        params.put("lang", lang);
        WxUserInfoJson json = WxUtil.sendRequest(config.getUserOAuthUserInfoUrl(), HttpMethod.GET, params, null,
                WxUserInfoJson.class);

        return storageService.saveOrUpdateUser(json);
    }

    public static enum Scope {
        snsapi_base, snsapi_userinfo
    }
}
