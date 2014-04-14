/**
 * 
 */
package org.hamster.weixinmp.service;

import static org.hamster.weixinmp.util.WxUtil.getAccessTokenParams;
import static org.hamster.weixinmp.util.WxUtil.sendRequest;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.menu.WxMenuBtnEntity;
import org.hamster.weixinmp.dao.entity.user.WxUserEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.gson.WxMenuBtnSerializer;
import org.hamster.weixinmp.model.WxRespCode;
import org.hamster.weixinmp.model.menu.WxMenuCreateJson;
import org.hamster.weixinmp.model.menu.WxMenuGetJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 4, 2014
 * 
 */
@Service
public class WxMenuService {
	@Autowired
	WxConfig config;

	public WxRespCode menuCreate(String accessToken, List<WxMenuBtnEntity> entities)
			throws WxException {
		Map<String, String> params = getAccessTokenParams(accessToken);

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(WxUserEntity.class,
				new WxMenuBtnSerializer());
		Gson gson = gsonBuilder.create();
		
		WxMenuCreateJson wrapper = new WxMenuCreateJson(entities);
		wrapper.setButton(entities);

		try {
			return sendRequest(config.getMenuCreateUrl(), HttpMethod.POST, params,
					new StringEntity(gson.toJson(wrapper)),
					WxRespCode.class);
		} catch (UnsupportedEncodingException e) {
			throw new WxException(e);
		}
	}
	
	public List<WxMenuBtnEntity> menuGet(String accessToken) throws WxException {
		Map<String, String> params = getAccessTokenParams(accessToken);

		return sendRequest(config.getMenuGetUrl(), HttpMethod.GET, params, null,
				WxMenuGetJson.class).getMenu().getButton();
	}
	
	public WxRespCode menuDelete(String accessToken) throws WxException {
		Map<String, String> params = getAccessTokenParams(accessToken);
		return sendRequest(config.getMenuDeleteUrl(), HttpMethod.GET, params, null,
				WxRespCode.class);
	}
	

}
