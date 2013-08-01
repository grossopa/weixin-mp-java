/**
 * 
 */
package org.hamster.weixinmp.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.WxRespTypeEnum;
import org.hamster.weixinmp.controller.util.WxXmlUtil;
import org.hamster.weixinmp.dao.entity.auth.WxAuthReq;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEvent;
import org.hamster.weixinmp.dao.entity.msg.WxMsgImg;
import org.hamster.weixinmp.dao.entity.msg.WxMsgLink;
import org.hamster.weixinmp.dao.entity.msg.WxMsgLoc;
import org.hamster.weixinmp.dao.entity.msg.WxMsgText;
import org.hamster.weixinmp.dao.entity.resp.WxRespText;
import org.hamster.weixinmp.dao.repository.auth.WxAuthReqDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgEventDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgImgDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgLinkDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgLocDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgTextDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespMusicDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespPicDescDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespTextDao;
import org.hamster.weixinmp.exception.WxException;
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

	@Autowired(required = false)
	WxAuthReqDao authReqDao;

	@Autowired(required = false)
	WxMsgTextDao msgTextDao;
	@Autowired(required = false)
	WxMsgImgDao msgImgDao;
	@Autowired(required = false)
	WxMsgLinkDao msgLinkDao;
	@Autowired(required = false)
	WxMsgLocDao msgLocDao;
	@Autowired(required = false)
	WxMsgEventDao msgEventDao;

	@Autowired(required = false)
	WxRespTextDao respTextDao;
	@Autowired(required = false)
	WxRespPicDescDao respPicDescDao;
	@Autowired(required = false)
	WxRespMusicDao respMusicDao;

	@Setter
	private String token;

	@Autowired
	WxConfig wxConfig;

	public boolean validateAuth(String signature, String timestamp,
			String nonce, String echostr) throws WxException {
		if (StringUtils.isBlank(token)) {
			token = wxConfig.getToken();
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

	public WxRespText createRestText(String content, String fromUserName,
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

	private static String getStringToHash(String timestamp, String nonce,
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
	private static String hash(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] b = md.digest(str.getBytes());
			StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < b.length; i++) {
	          sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
	        }
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// never happens
		}
		return null;
	}

}
