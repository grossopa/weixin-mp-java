/**
 * 
 */
package org.hamster.weixinmp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hamster.weixinmp.constant.WxMsgRespType;
import org.hamster.weixinmp.constant.WxMsgRespTypeEnum;
import org.hamster.weixinmp.constant.WxMsgTypeEnum;
import org.hamster.weixinmp.controller.util.WxXmlUtil;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespImageEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespMusicEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespPicDescEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespTextEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespVideoEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespVoiceEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.handler.WxMessageHandlerIfc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 当普通微信用户向公众账号发消息时，微信服务器将POST消息的XML数据包到开发者填写的URL上。各消息类型的推送XML数据包结构如下。
 * 
 * <p>微信服务器在五秒内收不到响应会断掉连接，并且重新发起请求，总共重试三次</p>
 *
 * <p>关于重试的消息排重，推荐使用msgid排重。</p>
 *
 * <p>假如服务器无法保证在五秒内处理并回复，可以直接回复空串，微信服务器不会对此作任何处理，并且不会发起重试。</p>
 * 
 * @author grossopaforever@gmail.com
 * @version 1.1
 */
@Service
public class WxMessageService {
	
	@Autowired(required=false)
	List<WxMessageHandlerIfc> handlers;
	
	public WxBaseMsgEntity parseXML(String xml) throws DocumentException,
			WxException {
		Element ele = DocumentHelper.parseText(xml).getRootElement();
		String msgType = null;
		if ((msgType = ele.elementText("MsgType")) == null) {
			throw new WxException("cannot find MsgType Node!\n" + xml);
		}
		WxMsgTypeEnum msgTypeEnum = WxMsgTypeEnum.inst(msgType);
		switch (msgTypeEnum) {
		case EVENT:
			return WxXmlUtil.getMsgEvent(ele);
		case IMAGE:
			return WxXmlUtil.getMsgImage(ele);
		case LINK:
			return WxXmlUtil.getMsgLink(ele);
		case LOCATION:
			return WxXmlUtil.getMsgLoc(ele);
		case TEXT:
			return WxXmlUtil.getMsgText(ele);
		case VIDEO:
			return WxXmlUtil.getMsgVideo(ele);
		case VOICE:
			return WxXmlUtil.getMsgVoice(ele);
		default:
			// never happens
			break;
		}
		return null;
	}
	
	public WxBaseRespEntity handleMessage(WxBaseMsgEntity msg) {
		List<WxMessageHandlerIfc> handlerList = new ArrayList<WxMessageHandlerIfc>();
		handlerList.addAll(handlers);
		Collections.sort(handlerList, new WxMessageHandlerComparator());
		
		Map<String, Object> context = new HashMap<String, Object>();
		WxBaseRespEntity result = null;
		for (WxMessageHandlerIfc handler : handlerList) {
			result = handler.handle(msg, context);
		}
		
		if (result == null) {
			result = defaultResult(msg.getToUserName(), msg.getFromUserName());
		}
		return result;
	}
	
	public Element parseRespXML(WxBaseRespEntity resp) throws DocumentException {
		WxMsgRespTypeEnum type = WxMsgRespTypeEnum.inst(resp.getMsgType());
		switch (type) {
		case IMAGE:
			return WxXmlUtil.getRespImage((WxRespImageEntity) resp);
		case MUSIC:
			return WxXmlUtil.getRespMusic((WxRespMusicEntity) resp, ((WxRespMusicEntity) resp).getThumb());
		case NEWS:
			return WxXmlUtil.getRespPicDesc((WxRespPicDescEntity) resp);
		case TEXT:
			return WxXmlUtil.getRespTextXML((WxRespTextEntity) resp);
		case VIDEO:
			return WxXmlUtil.getRespVideo((WxRespVideoEntity) resp);
		case VOICE:
			return WxXmlUtil.getRespVoice((WxRespVoiceEntity) resp);
		default:
			break;
		}
		return null;
	}
	
	protected WxRespTextEntity defaultResult(String fromUserName, String toUserName) {
		WxRespTextEntity result = new WxRespTextEntity();
		result.setContent("您好,您的消息已收到.");
		result.setCreatedDate(new Date());
		result.setCreateTime(new Date().getTime() / 1000);
		result.setFromUserName(fromUserName);
		result.setMsgType(WxMsgRespType.TEXT);
		result.setToUserName(toUserName);
		return result;
	}
	
}

class WxMessageHandlerComparator implements Comparator<WxMessageHandlerIfc> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(WxMessageHandlerIfc o1, WxMessageHandlerIfc o2) {
		if (o1.priority() > o2.priority()) {
			return -1;
		} else if (o1.priority() < o2.priority()) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
