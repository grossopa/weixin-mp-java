/**
 * 
 */
package org.hamster.weixinmp.service;

import static org.hamster.weixinmp.util.WxUtil.getAccessTokenParams;
import static org.hamster.weixinmp.util.WxUtil.sendRequest;
import static org.hamster.weixinmp.util.WxUtil.toJsonStringEntity;

import java.util.Map;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.qr.WxQrEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.model.qr.WxQrActionInfoJson;
import org.hamster.weixinmp.model.qr.WxQrCreateJson;
import org.hamster.weixinmp.model.qr.WxQrSceneJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 4, 2014
 * 
 */
@Service
public class WxQrService {
	@Autowired
	private WxConfig config;

	public WxQrEntity remoteQrcodeCreate(String accessToken,
			boolean isTemporary, Long sceneId, Long expire_seconds)
			throws WxException {
		Map<String, String> params = getAccessTokenParams(accessToken);

		WxQrSceneJson scene = new WxQrSceneJson();
		scene.setScene_id(sceneId);
		WxQrActionInfoJson action = new WxQrActionInfoJson();
		action.setScene(scene);
		WxQrCreateJson requestJson = new WxQrCreateJson();
		requestJson.setAction_name(isTemporary ? "QR_LIMIT_SCENE" : "QR_SCENE");
		requestJson.setAction_info(action);
		if (isTemporary) {
			requestJson.setExpire_seconds(expire_seconds);
		}

		WxQrEntity result = sendRequest(config.getQrcodeCreateUrl(),
				HttpMethod.POST, params, toJsonStringEntity(requestJson),
				WxQrEntity.class);
		result.setScene(sceneId);
		return result;
	}

}
