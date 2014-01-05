/**
 * 
 */
package org.hamster.weixinmp.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.auth.WxAuth;
import org.hamster.weixinmp.dao.entity.auth.WxAuthReq;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.util.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 1, 2014
 * 
 */
@Service
public class WxAuthService {
	
	private static final Logger log = LoggerFactory.getLogger(WxAuthService.class);
	
	@Autowired
	protected WxConfig config;

	public WxAuth getAccessToken(String appid, String appsecret)
			throws WxException {
		Map<String, String> paramsJson = new HashMap<String, String>();
		paramsJson.put("grant_type", "client_credential");
		paramsJson.put("appid", appid);
		paramsJson.put("secret", appsecret);

		WxAuth result = WxUtil.sendRequest(config.getAccessTokenCreateUrl(),
				HttpMethod.GET, paramsJson, null, WxAuth.class);
		result.setGrantType("client_credential");
		result.setAppid(appid);
		result.setSecret(appsecret);
		return result;
	}

	public boolean validateAuth(String signature, String timestamp,
			String nonce, String echostr) throws WxException {
		WxAuthReq authReq = new WxAuthReq();
		authReq.setCreatedDate(new Date());
		authReq.setSignature(signature);
		authReq.setTimestamp(timestamp);
		authReq.setNonce(nonce);
		authReq.setEchostr(echostr);

		String excepted = hash(getStringToHash(timestamp, nonce,
				config.getToken()));

		if (signature == null || !signature.equals(excepted)) {
			log.error("Authentication failed! excepted echostr ->" + excepted);
			log.error("                                 actual ->" + signature);
			return false;
		}

		return true;
	}

	protected static String getStringToHash(String timestamp, String nonce,
			String token) {
		List<String> list = new ArrayList<String>();
		list.add(timestamp);
		list.add(nonce);
		list.add(token);

		String result = "";
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			result += list.get(i);
		}
		return result;
	}

	protected static String hash(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] b = md.digest(str.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// never happens
		}
		return null;
	}

}
