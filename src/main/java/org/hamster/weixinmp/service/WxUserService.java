/**
 * 
 */
package org.hamster.weixinmp.service;

import static org.hamster.weixinmp.util.WxUtil.getAccessTokenParams;
import static org.hamster.weixinmp.util.WxUtil.sendRequest;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.user.WxUserEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.model.user.WxUserGetJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * @author grossopaforever@gmail.com
 * @version Dec 31, 2013
 * 
 */
@Service
public class WxUserService {

    @Autowired
    private WxConfig config;

    /**
     * <p>
     * <b>获取用户基本信息</b>
     * </p>
     * 
     * 在关注者与公众号产生消息交互后，公众号可获得关注者的OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的。对于不同公众号，同一用户的
     * openid不同）。公众号可通过本接口来根据OpenID获取用户基本信息，包括昵称、头像、性别、所在城市、语言和关注时间。<br>
     * 
     * @param accessToken
     *            调用接口凭证
     * @param openId
     *            普通用户的标识，对当前公众号唯一
     * @param lang
     *            返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     * @return
     * @throws WxException
     */
    public WxUserEntity remoteUserInfo(String accessToken, String openId, String lang) throws WxException {
        Map<String, String> params = getAccessTokenParams(accessToken);
        params.put("openid", openId);
        if (StringUtils.isNotBlank(lang)) {
            params.put("lang", lang);
        }
        return sendRequest(config.getUserInfoUrl(), HttpMethod.GET, params, null, WxUserEntity.class);
    }

    /**
     * <p>
     * <b>获取关注者列表</b>
     * </p>
     * 
     * 公众号可通过本接口来获取帐号的关注者列表，关注者列表由一串OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。
     * 一次拉取调用最多拉取10000个关注者的OpenID，可以通过多次拉取的方式来满足需求。<br>
     * 
     * @param accessToken
     * @param nextOpenId
     * @return
     * @throws WxException
     */
    public WxUserGetJson remoteUserGet(String accessToken, String nextOpenId) throws WxException {
        Map<String, String> params = getAccessTokenParams(accessToken);
        if (!StringUtils.isBlank(nextOpenId)) {
            params.put("next_openid", nextOpenId);
        }
        return sendRequest(config.getUserGetUrl(), HttpMethod.GET, params, null, WxUserGetJson.class);
    }

    public WxUserGetJson remoteUserGet(String accessToken) throws WxException {
        return remoteUserGet(accessToken, "");
    }
}