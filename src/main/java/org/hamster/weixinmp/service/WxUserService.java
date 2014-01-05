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

	public WxUserEntity remoteUserInfo(String accessToken, String openId) throws WxException {
		Map<String, String> params = getAccessTokenParams(accessToken);
		params.put("openid", openId);
		return sendRequest(config.getUserInfoUrl(), HttpMethod.GET,
				params, null, WxUserEntity.class);
 	}
	
	public WxUserGetJson remoteUserGet(String accessToken, String nextOpenId) throws WxException {
		Map<String, String> params = getAccessTokenParams(accessToken);
		if (!StringUtils.isBlank(nextOpenId)) {
			params.put("next_openid", nextOpenId);
		}
		return sendRequest(config.getUserGetUrl(), HttpMethod.GET,
				params, null, WxUserGetJson.class);
	}
	
	public WxUserGetJson remoteUserGet(String accessToken) throws WxException {
		return remoteUserGet(accessToken, "");
	}
}