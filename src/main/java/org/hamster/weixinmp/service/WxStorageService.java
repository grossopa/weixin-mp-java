/**
 * 
 */
package org.hamster.weixinmp.service;

import java.util.Date;
import java.util.List;

import lombok.Setter;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.WxMsgRespType;
import org.hamster.weixinmp.controller.util.WxXmlUtil;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemMusicEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemPicDescEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgImageEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgLinkEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgLocEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgTextEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespMusicEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespPicDescEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespTextEntity;
import org.hamster.weixinmp.dao.repository.auth.WxAuthDao;
import org.hamster.weixinmp.dao.repository.auth.WxAuthReqDao;
import org.hamster.weixinmp.dao.repository.item.WxItemImageDao;
import org.hamster.weixinmp.dao.repository.item.WxItemMusicDao;
import org.hamster.weixinmp.dao.repository.item.WxItemPicDescDao;
import org.hamster.weixinmp.dao.repository.item.WxItemThumbDao;
import org.hamster.weixinmp.dao.repository.item.WxItemVideoDao;
import org.hamster.weixinmp.dao.repository.item.WxItemVoiceDao;
import org.hamster.weixinmp.dao.repository.menu.WxMenuBtnDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgEventDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgImageDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgLinkDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgLocDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgTextDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgVideoDao;
import org.hamster.weixinmp.dao.repository.msg.WxMsgVoiceDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespImageDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespMusicDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespPicDescDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespTextDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespVideoDao;
import org.hamster.weixinmp.dao.repository.resp.WxRespVoiceDao;
import org.hamster.weixinmp.dao.repository.user.WxGroupDao;
import org.hamster.weixinmp.dao.repository.user.WxUserDao;
import org.hamster.weixinmp.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Service
@Transactional(readOnly = true)
public class WxStorageService {
	public static final Logger log = Logger.getLogger(WxStorageService.class);

	@Autowired(required = false)
	protected WxAuthDao authDao;
	@Autowired(required = false)
	protected WxAuthReqDao authReqDao;

	@Autowired(required = false)
	protected WxMsgTextDao msgTextDao;
	@Autowired(required = false)
	protected WxMsgImageDao msgImgDao;
	@Autowired(required = false)
	protected WxMsgLinkDao msgLinkDao;
	@Autowired(required = false)
	protected WxMsgLocDao msgLocDao;
	@Autowired(required = false)
	protected WxMsgEventDao msgEventDao;
	@Autowired(required = false)
	protected WxMsgVideoDao msgVideoDao;
	@Autowired(required = false)
	protected WxMsgVoiceDao msgVoiceDao;

	@Autowired(required = false)
	protected WxRespTextDao respTextDao;
	@Autowired(required = false)
	protected WxRespPicDescDao respPicDescDao;
	@Autowired(required = false)
	protected WxRespMusicDao respMusicDao;
	@Autowired(required = false)
	protected WxRespImageDao respImageDao;
	@Autowired(required = false)
	protected WxRespVideoDao respVideoDao;
	@Autowired(required = false)
	protected WxRespVoiceDao respVoiceDao;
	
	@Autowired(required = false)
	protected WxItemImageDao itemImageDao;
	@Autowired(required = false)
	protected WxItemMusicDao itemMusicDao;
	@Autowired(required = false)
	protected WxItemPicDescDao wxItemPicDescDao;
	@Autowired(required = false)
	protected WxItemThumbDao itemThumbDao;
	@Autowired(required = false)
	protected WxItemVideoDao itemVideoDao;
	@Autowired(required = false)
	protected WxItemVoiceDao itemVoiceDao;

	@Autowired(required = false)
	protected WxMenuBtnDao wxMenuBtnDao;
	
	@Autowired(required = false)
	protected WxGroupDao groupDao;
	@Autowired(required = false)
	protected WxUserDao userDao;

	@Setter
	protected String token;

	@Autowired(required = false)
	protected WxConfig wxConfig;

	public WxMsgTextEntity saveMsgText(Element ele) throws DocumentException {
		WxMsgTextEntity msgText = WxXmlUtil.getMsgText(ele);
		if (msgTextDao != null) {
			msgTextDao.save(msgText);
		} else {

		}
		return msgText;
	}

	public WxMsgImageEntity saveMsgImg(Element ele) throws DocumentException {
		WxMsgImageEntity msgImg = WxXmlUtil.getMsgImage(ele);
		if (msgImgDao != null) {
			msgImgDao.save(msgImg);
		} else {

		}
		return msgImg;
	}

	public WxMsgLinkEntity saveMsgLink(Element ele) throws DocumentException {
		WxMsgLinkEntity msgLink = WxXmlUtil.getMsgLink(ele);
		if (msgLinkDao != null) {
			msgLinkDao.save(msgLink);
		} else {

		}
		return msgLink;
	}

	public WxMsgLocEntity saveMsgLoc(Element ele) throws DocumentException {
		WxMsgLocEntity msgLoc = WxXmlUtil.getMsgLoc(ele);
		if (msgLocDao != null) {
			msgLocDao.save(msgLoc);
		} else {

		}
		return msgLoc;
	}

	public WxMsgEventEntity saveMsgEvent(Element ele) throws DocumentException {
		WxMsgEventEntity msgEvent = WxXmlUtil.getMsgEvent(ele);
		if (msgEventDao != null) {
			msgEventDao.save(msgEvent);
		} else {

		}
		return msgEvent;
	}

	public WxRespTextEntity createRespText(String content, String fromUserName,
			String toUserName, Integer funcFlag) {
		WxRespTextEntity respText = new WxRespTextEntity();
		respText.setContent(content);
		respText.setCreatedDate(new Date());
		respText.setCreateTime(WxUtil.currentTimeInSec());
		respText.setFromUserName(fromUserName);
		respText.setToUserName(toUserName);
		respText.setFuncFlag(funcFlag);
		respText.setMsgType(WxMsgRespType.TEXT);
		if (respTextDao != null) {
			respTextDao.save(respText);
		} else {

		}
		return respText;
	}

	public WxRespPicDescEntity createRespPicDesc(
			List<WxItemPicDescEntity> articles, String fromUserName,
			String toUserName, Integer funcFlag) {
		WxRespPicDescEntity respPicDesc = new WxRespPicDescEntity();
		respPicDesc.setCreatedDate(new Date());
		respPicDesc.setCreateTime(WxUtil.currentTimeInSec());
		respPicDesc.setFromUserName(fromUserName);
		respPicDesc.setToUserName(toUserName);
		respPicDesc.setFuncFlag(funcFlag);
		respPicDesc.setMsgType(WxMsgRespType.NEWS);
		respPicDesc.setArticles(articles);
		if (respPicDescDao != null) {
			respPicDescDao.save(respPicDesc);
		} else {

		}
		return respPicDesc;
	}

	public WxRespPicDescEntity createRespPicDesc2(List<Long> articleIds,
			String fromUserName, String toUserName, Integer funcFlag) {
		WxRespPicDescEntity respPicDesc = new WxRespPicDescEntity();
		respPicDesc.setCreatedDate(new Date());
		respPicDesc.setCreateTime(WxUtil.currentTimeInSec());
		respPicDesc.setFromUserName(fromUserName);
		respPicDesc.setToUserName(toUserName);
		respPicDesc.setFuncFlag(funcFlag);
		respPicDesc.setMsgType(WxMsgRespType.NEWS);
		respPicDesc.setArticles(wxItemPicDescDao.findByIdIn(articleIds));
		if (respPicDescDao != null) {
			respPicDescDao.save(respPicDesc);
		} else {

		}
		return respPicDesc;
	}

	public WxRespMusicEntity createRespMusic(String fromUserName,
			String toUserName, Integer funcFlag, WxItemMusicEntity itemMusic) {
		WxRespMusicEntity respMusic = new WxRespMusicEntity();
		respMusic.setCreatedDate(new Date());
		respMusic.setCreateTime(WxUtil.currentTimeInSec());
		respMusic.setFromUserName(fromUserName);
		respMusic.setToUserName(toUserName);
		respMusic.setFuncFlag(funcFlag);
		respMusic.setMsgType(WxMsgRespType.MUSIC);
		// respMusic.setMusic(itemMusic);
		if (respMusicDao != null) {
			respMusicDao.save(respMusic);
		} else {

		}
		return respMusic;
	}

	// ///////////////
	// common parts //
	// ///////////////
	public WxBaseRespEntity handleMessage(WxBaseMsgEntity msg) {
		WxRespTextEntity respText = createRespText(
				"Only test message, please ignore this.", msg.getToUserName(),
				msg.getFromUserName(), 1);
		return respText;
	}
}
