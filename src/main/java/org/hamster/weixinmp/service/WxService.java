/**
 * 
 */
package org.hamster.weixinmp.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.WxRespTypeEnum;
import org.hamster.weixinmp.controller.util.WxJsonUtil;
import org.hamster.weixinmp.controller.util.WxXmlUtil;
import org.hamster.weixinmp.dao.entity.auth.WxAuth;
import org.hamster.weixinmp.dao.entity.auth.WxAuthReq;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemMusic;
import org.hamster.weixinmp.dao.entity.item.WxItemPicDesc;
import org.hamster.weixinmp.dao.entity.menu.WxMenuBtn;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEvent;
import org.hamster.weixinmp.dao.entity.msg.WxMsgImg;
import org.hamster.weixinmp.dao.entity.msg.WxMsgLink;
import org.hamster.weixinmp.dao.entity.msg.WxMsgLoc;
import org.hamster.weixinmp.dao.entity.msg.WxMsgText;
import org.hamster.weixinmp.dao.entity.resp.WxRespMusic;
import org.hamster.weixinmp.dao.entity.resp.WxRespPicDesc;
import org.hamster.weixinmp.dao.entity.resp.WxRespText;
import org.hamster.weixinmp.dao.repository.auth.WxAuthDao;
import org.hamster.weixinmp.dao.repository.auth.WxAuthReqDao;
import org.hamster.weixinmp.dao.repository.item.WxItemPicDescDao;
import org.hamster.weixinmp.dao.repository.menu.WxMenuBtnDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgEventDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgImgDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgLinkDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgLocDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgTextDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespMusicDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespPicDescDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespTextDao;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.model.WxAccessTokenJson;
import org.hamster.weixinmp.model.WxErrorJson;
import org.hamster.weixinmp.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Component
@Transactional
public class WxService {
	public static final Logger log = Logger.getLogger(WxService.class);

	public static final String DEFAULT_MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public static final String DEFAULT_GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	@Autowired(required = false)
	protected WxAuthDao wxAuthDao;
	@Autowired(required = false)
	protected WxAuthReqDao authReqDao;

	@Autowired(required = false)
	protected WxMsgTextDao msgTextDao;
	@Autowired(required = false)
	protected WxMsgImgDao msgImgDao;
	@Autowired(required = false)
	protected WxMsgLinkDao msgLinkDao;
	@Autowired(required = false)
	protected WxMsgLocDao msgLocDao;
	@Autowired(required = false)
	protected WxMsgEventDao msgEventDao;

	@Autowired(required = false)
	protected WxRespTextDao respTextDao;
	@Autowired(required = false)
	protected WxRespPicDescDao respPicDescDao;
	@Autowired(required = false)
	protected WxRespMusicDao respMusicDao;
	@Autowired(required = false)
	protected WxItemPicDescDao wxItemPicDescDao;

	@Autowired(required = false)
	protected WxMenuBtnDao wxMenuBtnDao;

	@Setter
	protected String token;

	@Autowired(required = false)
	protected WxConfig wxConfig;

	public boolean validateAuth(String signature, String timestamp,
			String nonce, String echostr) throws WxException {
		if (StringUtils.isBlank(token)) {
			if (wxConfig != null) {
				token = wxConfig.getToken();
			}
			if (StringUtils.isBlank(token)) {
				throw new WxException("token must not be blank!");
			}
		}
		WxAuthReq authReq = new WxAuthReq();
		authReq.setCreatedDate(new Date());
		authReq.setSignature(signature);
		authReq.setTimestamp(timestamp);
		authReq.setNonce(nonce);
		authReq.setEchostr(echostr);

		String excepted = hash(getStringToHash(timestamp, nonce, token));

		if (signature == null || !signature.equals(excepted)) {
			log.error("Authentication failed! excepted echostr ->" + excepted);
			log.error("                                 actual ->" + signature);
			return false;
		}

		if (authReqDao != null) {
			authReqDao.save(authReq);
		} else {

		}
		return true;
	}

	public WxMsgText saveMsgText(Element ele) throws DocumentException {
		WxMsgText msgText = WxXmlUtil.getMsgText(ele);
		if (msgTextDao != null) {
			msgTextDao.save(msgText);
		} else {

		}
		return msgText;
	}

	public WxMsgImg saveMsgImg(Element ele) throws DocumentException {
		WxMsgImg msgImg = WxXmlUtil.getMsgImg(ele);
		if (msgImgDao != null) {
			msgImgDao.save(msgImg);
		} else {

		}
		return msgImg;
	}

	public WxMsgLink saveMsgLink(Element ele) throws DocumentException {
		WxMsgLink msgLink = WxXmlUtil.getMsgLink(ele);
		if (msgLinkDao != null) {
			msgLinkDao.save(msgLink);
		} else {

		}
		return msgLink;
	}

	public WxMsgLoc saveMsgLoc(Element ele) throws DocumentException {
		WxMsgLoc msgLoc = WxXmlUtil.getMsgLoc(ele);
		if (msgLocDao != null) {
			msgLocDao.save(msgLoc);
		} else {

		}
		return msgLoc;
	}

	public WxMsgEvent saveMsgEvent(Element ele) throws DocumentException {
		WxMsgEvent msgEvent = WxXmlUtil.getMsgEvent(ele);
		if (msgEventDao != null) {
			msgEventDao.save(msgEvent);
		} else {

		}
		return msgEvent;
	}

	public WxRespText createRespText(String content, String fromUserName,
			String toUserName, Integer funcFlag) {
		WxRespText respText = new WxRespText();
		respText.setContent(content);
		respText.setCreatedDate(new Date());
		respText.setCreateTime(WxUtil.currentTimeInSec());
		respText.setFromUserName(fromUserName);
		respText.setToUserName(toUserName);
		respText.setFuncFlag(funcFlag);
		respText.setMsgType(WxRespTypeEnum.text.toString());
		if (respTextDao != null) {
			respTextDao.save(respText);
		} else {

		}
		return respText;
	}

	public WxRespPicDesc createRespPicDesc(List<WxItemPicDesc> articles,
			String fromUserName, String toUserName, Integer funcFlag) {
		WxRespPicDesc respPicDesc = new WxRespPicDesc();
		respPicDesc.setCreatedDate(new Date());
		respPicDesc.setCreateTime(WxUtil.currentTimeInSec());
		respPicDesc.setFromUserName(fromUserName);
		respPicDesc.setToUserName(toUserName);
		respPicDesc.setFuncFlag(funcFlag);
		respPicDesc.setMsgType(WxRespTypeEnum.news.toString());
		respPicDesc.setArticles(articles);
		if (respPicDescDao != null) {
			respPicDescDao.save(respPicDesc);
		} else {

		}
		return respPicDesc;
	}

	public WxRespPicDesc createRespPicDesc2(List<Long> articleIds,
			String fromUserName, String toUserName, Integer funcFlag) {
		WxRespPicDesc respPicDesc = new WxRespPicDesc();
		respPicDesc.setCreatedDate(new Date());
		respPicDesc.setCreateTime(WxUtil.currentTimeInSec());
		respPicDesc.setFromUserName(fromUserName);
		respPicDesc.setToUserName(toUserName);
		respPicDesc.setFuncFlag(funcFlag);
		respPicDesc.setMsgType(WxRespTypeEnum.news.toString());
		respPicDesc.setArticles(wxItemPicDescDao.findByIdIn(articleIds));
		if (respPicDescDao != null) {
			respPicDescDao.save(respPicDesc);
		} else {

		}
		return respPicDesc;
	}

	public WxRespMusic createRespMusic(String fromUserName, String toUserName,
			Integer funcFlag, WxItemMusic itemMusic) {
		WxRespMusic respMusic = new WxRespMusic();
		respMusic.setCreatedDate(new Date());
		respMusic.setCreateTime(WxUtil.currentTimeInSec());
		respMusic.setFromUserName(fromUserName);
		respMusic.setToUserName(toUserName);
		respMusic.setFuncFlag(funcFlag);
		respMusic.setMsgType(WxRespTypeEnum.music.toString());
		respMusic.setMusic(itemMusic);
		if (respMusicDao != null) {
			respMusicDao.save(respMusic);
		} else {

		}
		return respMusic;
	}

	// ///////////////
	// common parts //
	// ///////////////
	public WxAuth getAccessToken(String appid, String appsecret)
			throws WxException {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet();
		try {
			if (wxConfig != null) {
				get.setURI(new URI(getParameterizedUrl(
						wxConfig.getMenuCreateUrl(), "APPID", appid,
						"APPSECRET", appsecret)));
			} else {
				get.setURI(new URI(getParameterizedUrl(DEFAULT_GET_TOKEN_URL,
						"APPID", appid, "APPSECRET", appsecret)));
			}
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String respBody = EntityUtils.toString(entity);
			if (entity != null) {
				EntityUtils.consume(entity);
			}
			WxErrorJson errorObj = WxJsonUtil.toWxErrorJson(respBody);
			if (errorObj != null) {
				throw new WxException(errorObj);
			} else {
				WxAccessTokenJson tokenJson = WxJsonUtil.toAccessTokenJson(respBody);
				WxAuth auth = new WxAuth("client_credential", appid, appsecret, tokenJson.getAccess_token());
				if (wxAuthDao != null) {
					wxAuthDao.save(auth);
				} else {
					
				}
				return auth;
			}
		} catch (URISyntaxException e) {
			throw new WxException(e);
		} catch (IOException e) {
			throw new WxException(e);
		}
	}

	// /////////////
	// menu parts //
	// /////////////
	public void createMenu(List<WxMenuBtn> wxMenuBtnList, String accessToken)
			throws WxException {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost();
		try {
			post.setURI(new URI(wxConfig.getMenuCreateUrl()));
			if (wxConfig != null) {
				post.setURI(new URI(getParameterizedUrl(
						wxConfig.getMenuCreateUrl(), "ACCESS_TOKEN",
						accessToken)));
			} else {
				post.setURI(new URI(getParameterizedUrl(
						DEFAULT_MENU_CREATE_URL, "ACCESS_TOKEN", accessToken)));
			}
			post.setEntity(new StringEntity(WxJsonUtil
					.toMenuCreateReqBody(wxMenuBtnList)));
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			String respBody = EntityUtils.toString(entity);
			if (entity != null) {
				EntityUtils.consume(entity);
			}
			WxErrorJson errorObj = WxJsonUtil.toWxErrorJson(respBody);
			if (errorObj != null) {
				throw new WxException(errorObj);
			}
		} catch (URISyntaxException e) {
			throw new WxException(e);
		} catch (IOException e) {
			throw new WxException(e);
		}
	}
	
	public WxBaseRespEntity handleMessage(WxBaseMsgEntity msg) {
		WxRespText respText = createRespText("Only test message, please ignore this.", msg.getToUserName(), msg.getFromUserName(), 1);
		return respText;
	}

	protected static String getParameterizedUrl(String url, String... args) {
		String result = url;
		for (int i = 0; i < args.length; i += 2) {
			String p = args[i];
			String v = args[i + 1];
			result = result.replaceAll(p, v);
		}
		return result;
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

	/**
	 */
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
