/**
 * 
 */
package org.hamster.weixinmp.controller.util;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hamster.weixinmp.constant.WxMsgTypeEnum;
import org.hamster.weixinmp.dao.entity.auth.WxAuthReq;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemImageEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemPicDescEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemThumbEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemVideoEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemVoiceEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgImageEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgLinkEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgLocEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgTextEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgVideoEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgVoiceEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespImageEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespMusicEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespPicDescEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespTextEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespVideoEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespVoiceEntity;
import org.hamster.weixinmp.util.WxUtil;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
public class WxXmlUtil {

	private WxXmlUtil() {
	}

	public static WxAuthReq getAuthReq(String signature, String timestamp,
			String nonce, String echostr) {
		WxAuthReq result = new WxAuthReq();
		result.setSignature(signature);
		result.setTimestamp(timestamp);
		result.setNonce(nonce);
		result.setEchostr(echostr);
		return result;
	}

	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/
	 * FromUserName&gt; <br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;1348831860&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[text]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Content&gt;&lt;![CDATA[this is a
	 * test]]&gt;&lt;/Content&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;<br />
	 * &lt;/xml&gt;<br />
	 * </code>
	 * 
	 * @param xmlstr
	 * @return
	 * @throws DocumentException
	 */
	public static WxMsgTextEntity getMsgText(Element ele) throws DocumentException {
		WxMsgTextEntity result = msgEntityFactory(WxMsgTextEntity.class, ele);
		result.setMsgId(longVal(ele, "MsgId"));
		result.setContent(strVal(ele, "Content"));
		return result;
	}
	
	/**
	 * <code>
	 * &lt;xml&gt;
 	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
     * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
 	 * &nbsp;&nbsp;&lt;CreateTime&gt;1348831860&lt;/CreateTime&gt;
     * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[image]]&gt;&lt;/MsgType&gt;
     * &nbsp;&nbsp;&lt;PicUrl&gt;&lt;![CDATA[this is a url]]&gt;&lt;/PicUrl&gt;
     * &nbsp;&nbsp;&lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;
     * &nbsp;&nbsp;&lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;
     * &lt;/xml&gt;
	 * </code>
	 * 
	 * @param xmlstr
	 * @return
	 * @throws DocumentException
	 */
	public static WxMsgImageEntity getMsgImage(Element ele) throws DocumentException {
		WxMsgImageEntity result = msgEntityFactory(WxMsgImageEntity.class, ele);
		WxItemImageEntity image = new WxItemImageEntity();
		image.setMediaId(strVal(ele, "MediaId"));
		image.setPicUrl(strVal(ele, "PicUrl"));
		result.setImage(image);
		return result;
	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;1357290913&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[voice]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;<br />
	 * &nbsp;&nbsp;&lt;Format&gt;&lt;![CDATA[Format]]&gt;&lt;/Format&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;<br />
	 * &lt;/xml&gt;
	 * </code>
	 * 
	 * @param ele
	 * @return
	 * @throws DocumentException
	 */
	public static WxMsgVoiceEntity getMsgVoice(Element ele) throws DocumentException {
		WxMsgVoiceEntity result = msgEntityFactory(WxMsgVideoEntity.class, ele);
		WxItemVoiceEntity voice = new WxItemVoiceEntity();
		voice.setMediaId(strVal(ele, "MediaId"));
		voice.setFormat(strVal(ele, "Format"));
		if (!StringUtils.isEmpty(ele.elementText("Recognition"))) {
			voice.setRecognition(strVal(ele, "Recognition"));
		}
		result.setVoice(voice);
		return result;
	}
	
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;1357290913&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[video]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;<br />
	 * &nbsp;&nbsp;&lt;ThumbMediaId&gt;&lt;![CDATA[thumb_media_id]]&gt;&lt;/ThumbMediaId&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;<br />
	 * &lt;/xml&gt;
	 * </code>
	 * @param ele
	 * @return
	 * @throws DocumentException
	 */
	public static WxMsgVideoEntity getMsgVideo(Element ele) throws DocumentException {
		WxMsgVideoEntity result = msgEntityFactory(WxMsgVideoEntity.class, ele);
		WxItemVideoEntity video = new WxItemVideoEntity();
		video.setMediaId(strVal(ele, "MediaId"));
		WxItemThumbEntity thumb = new WxItemThumbEntity();
		thumb.setMediaId(strVal(ele, "ThumbMediaId"));
		video.setThumb(thumb);
		result.setVideo(video);
		return result;
	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;1351776360&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[location]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Location_X&gt;23.134521&lt;/Location_X&gt;<br />
	 * &nbsp;&nbsp;&lt;Location_Y&gt;113.358803&lt;/Location_Y&gt;<br />
	 * &nbsp;&nbsp;&lt;Scale&gt;20&lt;/Scale&gt;<br />
	 * &nbsp;&nbsp;&lt;Label&gt;&lt;![CDATA[Location Information]]&gt;&lt;/Label&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;<br />
	 * &lt;/xml&gt;
	 * </code>
	 * 
	 * 
	 * @param xmlstr
	 * @return
	 * @throws DocumentException
	 */
	public static WxMsgLocEntity getMsgLoc(Element ele) throws DocumentException {
		WxMsgLocEntity result = msgEntityFactory(WxMsgLocEntity.class, ele);
		result.setLabel(strVal(ele, "Label"));
		result.setLocationX(doubleVal(ele, "Location_X"));
		result.setLocationY(doubleVal(ele, "Location_Y"));
		result.setScale(doubleVal(ele, "Scale"));
		return result;
	}

	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;1351776360&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[link]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Title&gt;&lt;![CDATA[公众平台官网链接]]&gt;&lt;/Title&gt;<br />
	 * &nbsp;&nbsp;&lt;Description&gt;&lt;![CDATA[公众平台官网链接]]&gt;&lt;/Description&gt;<br />
	 * &nbsp;&nbsp;&lt;Url&gt;&lt;![CDATA[url]]&gt;&lt;/Url&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;<br />
	 * &lt;/xml&gt; 
	 * </code>
	 * 
	 * @param xmlstr
	 * @return
	 * @throws DocumentException
	 */
	public static WxMsgLinkEntity getMsgLink(Element ele) throws DocumentException {
		WxMsgLinkEntity result = msgEntityFactory(WxMsgLinkEntity.class, ele);
		result.setTitle(strVal(ele, "Title"));
		result.setDescription(strVal(ele, "Description"));
		result.setUrl(strVal(ele, "Url"));
		return result;
	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[FromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;123456789&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[event]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Event&gt;&lt;![CDATA[EVENT]]&gt;&lt;/Event&gt;<br />
	 * &nbsp;&nbsp;&lt;EventKey&gt;&lt;![CDATA[EVENTKEY]]&gt;&lt;/EventKey&gt;<br />
	 * &lt;/xml&gt;
	 * </code>
	 * 
	 * @param xmlstr
	 * @return
	 * @throws DocumentException
	 */
	public static WxMsgEventEntity getMsgEvent(Element ele) throws DocumentException {
		WxMsgEventEntity result = msgEntityFactory(WxMsgEventEntity.class, ele);
		result.setEvent(strVal(ele, "Event"));
		if (ele.elementText("EventKey") != null) {
			result.setEventKey(strVal(ele, "EventKey"));
		}
		if (ele.elementText("Ticket") != null) {
			result.setEventKey(strVal(ele, "Ticket"));
		}
		return result;
	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[text]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Content&gt;&lt;![CDATA[content]]&gt;&lt;/Content&gt;<br />
	 * &nbsp;&nbsp;&lt;FuncFlag&gt;0&lt;/FuncFlag&gt;<br />
	 * &lt;/xml&gt;
	 * </code>
	 * 
	 * @param respText
	 * @return
	 * @throws DocumentException
	 */
	public static Element getRespTextXML(WxRespTextEntity respText) throws DocumentException {
		Element ele = respEntityFactory(respText);
		ele.addElement("Content").addCDATA(respText.getContent());
		return ele;
	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[image]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Image&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;<br />
	 * &nbsp;&nbsp;&lt;/Image&gt;<br />
	 * &lt;/xml&gt;<br />
	 * </code>
	 * 
	 * @param respImage
	 * @return
	 * @throws DocumentException
	 */
	public static Element getRespImage(WxRespImageEntity respImage) throws DocumentException {
		Element ele = respEntityFactory(respImage);
		Element imageEle = ele.addElement("Image");
		imageEle.addElement("MediaId").addCDATA(respImage.getImage().getMediaId());
		return ele;
	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[voice]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Voice&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;<br />
	 * &nbsp;&nbsp;&lt;/Voice&gt;<br />
	 * &lt;/xml&gt;<br />
	 * </code>
	 * 
	 * @param respVoice
	 * @return
	 * @throws DocumentException
	 */
	public static Element getRespVoice(WxRespVoiceEntity respVoice) throws DocumentException {
		Element ele = respEntityFactory(respVoice);
		Element voiceEle = ele.addElement("Voice");
		voiceEle.addElement("MediaId").addCDATA(respVoice.getVoice().getMediaId());
		return ele;
	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[video]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Video&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;Title&gt;&lt;![CDATA[title]]&gt;&lt;/Title&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;Description&gt;&lt;![CDATA[description]]&gt;&lt;/Description&gt;<br />
	 * &nbsp;&nbsp;&lt;/Video&gt;<br />
	 * &lt;/xml&gt;<br />
	 * </code>
	 * 
	 * @param respVideo
	 * @return
	 * @throws DocumentException
	 */
	public static Element getRespVideo(WxRespVideoEntity respVideo) throws DocumentException {
		Element ele = respEntityFactory(respVideo);
		Element videoEle = ele.addElement("Video");
		videoEle.addElement("MediaId").addCDATA(respVideo.getVideo().getMediaId());
		videoEle.addElement("Title").addCDATA(StringUtils.defaultString(respVideo.getVideo().getTitle()));
		videoEle.addElement("Description").addCDATA(StringUtils.defaultString(respVideo.getVideo().getDescription()));
		return ele;
	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[music]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Music&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;Title&gt;&lt;![CDATA[TITLE]]&gt;&lt;/Title&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;Description&gt;&lt;![CDATA[DESCRIPTION]]&gt;&lt;/Description&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;MusicUrl&gt;&lt;![CDATA[MUSIC_Url]]&gt;&lt;/MusicUrl&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;HQMusicUrl&gt;&lt;![CDATA[HQ_MUSIC_Url]]&gt;&lt;/HQMusicUrl&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;ThumbMediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/ThumbMediaId&gt;<br />
	 * &nbsp;&nbsp;&lt;/Music&gt;<br />
	 * &nbsp;&nbsp;&lt;FuncFlag&gt;0&lt;/FuncFlag&gt;<br />
	 * &lt;/xml&gt;
	 * </code>
	 * 
	 * @param respMusic
	 * @return
	 * @throws DocumentException
	 */
	public static Element getRespMusic(WxRespMusicEntity respMusic, WxItemThumbEntity thumb) throws DocumentException {
		Element ele = respEntityFactory(respMusic);
		Element musicEle = ele.addElement("Music");
		musicEle.addElement("Title").addCDATA(StringUtils.defaultString(respMusic.getMusic().getTitle()));
		musicEle.addElement("Description").addCDATA(StringUtils.defaultString(respMusic.getMusic().getDescription()));
		musicEle.addElement("MusicUrl").addCDATA(StringUtils.defaultString(respMusic.getMusic().getMusicUrl()));
		musicEle.addElement("HQMusicUrl").addCDATA(StringUtils.defaultString(respMusic.getMusic().getHqMusicUrl()));
		musicEle.addElement("ThumbMediaId").addCDATA(thumb.getMediaId());
		return ele;
	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
 	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
 	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
 	 * &nbsp;&nbsp;&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;<br />
 	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[news]]&gt;&lt;/MsgType&gt;<br />
 	 * &nbsp;&nbsp;&lt;ArticleCount&gt;2&lt;/ArticleCount&gt;<br />
 	 * &nbsp;&nbsp;&lt;Articles&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;item&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;Title&gt;&lt;![CDATA[title1]]&gt;&lt;/Title&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;Description&gt;&lt;![CDATA[description1]]&gt;&lt;/Description&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;PicUrl&gt;&lt;![CDATA[picurl]]&gt;&lt;/PicUrl&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;Url&gt;&lt;![CDATA[url]]&gt;&lt;/Url&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;/item&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;item&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;Title&gt;&lt;![CDATA[title]]&gt;&lt;/Title&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;Description&gt;&lt;![CDATA[description]]&gt;&lt;/Description&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;PicUrl&gt;&lt;![CDATA[picurl]]&gt;&lt;/PicUrl&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;Url&gt;&lt;![CDATA[url]]&gt;&lt;/Url&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;/item&gt;<br />
 	 * &nbsp;&nbsp;&lt;/Articles&gt;<br />
 	 * &nbsp;&nbsp;&lt;FuncFlag&gt;1&lt;/FuncFlag&gt;<br />
	 * &lt;/xml&gt; 
	 * </code>
	 * 
	 * @param respPicDesc
	 * @return
	 * @throws DocumentException
	 */
	public static Element getRespPicDesc(WxRespPicDescEntity respPicDesc) throws DocumentException {
		Element ele = respEntityFactory(respPicDesc);
		ele.addElement("ArticleCount").addText(String.valueOf(respPicDesc.getArticles().size()));
		Element articlesEle = ele.addElement("Articles");
		for (WxItemPicDescEntity item : respPicDesc.getArticles()) {
			Element itemEle = articlesEle.addElement("item");
			itemEle.addElement("Title").addCDATA(item.getTitle());
			itemEle.addElement("Description").addCDATA(item.getDescription());
			itemEle.addElement("PicUrl").addCDATA(item.getPicUrl());
			itemEle.addElement("HQMusicUrl").addCDATA(item.getUrl());
		}
		return ele;
	}
	
	public static Element toXML(String xmlstr) throws DocumentException {
		Document doc = DocumentHelper.parseText(xmlstr);
		return doc.getRootElement();
	}
	
	public static WxMsgTypeEnum getReqType(Element ele) {
		String type = ele.element("MsgType").getTextTrim();
		return WxMsgTypeEnum.inst(type);
	}
	
	
	//////////////////////////////////////////////////
	//                Private Methods               //
    //////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	private static <T> T msgEntityFactory(
			Class<? extends WxBaseMsgEntity> clazz, Element ele) {
		WxBaseMsgEntity result;
		try {
			result = clazz.newInstance();
			result.setToUserName(strVal(ele, "ToUserName"));
			result.setFromUserName(strVal(ele, "FromUserName"));
			result.setCreateTime(longVal(ele, "CreateTime"));
			result.setCreatedDate(new Date());
			result.setMsgType(strVal(ele, "MsgType"));
			if (ele.element("MsgId") != null) {
				result.setMsgId(longVal(ele, "MsgId"));
			}
			return (T) result;
		} catch (Exception e) {
			// never occurs
			return null;
		}
	}
	
	private static Element respEntityFactory(WxBaseRespEntity entity) {
		Element ele = DocumentHelper.createElement("xml");
		ele.addElement("ToUserName").addCDATA(entity.getToUserName());
		ele.addElement("FromUserName").addCDATA(entity.getFromUserName());
		String createTime = String.valueOf(entity.getCreateTime());
		if (StringUtils.isBlank(createTime)) {
			Long currentTime = WxUtil.currentTimeInSec();
			entity.setCreateTime(currentTime);
			createTime = String.valueOf(currentTime);
		}
		ele.addElement("CreateTime").setText(createTime);
		ele.addElement("MsgType").addCDATA(entity.getMsgType());
		ele.addElement("FuncFlag").setText(String.valueOf(entity.getFuncFlag()));
		return ele;
	}

	private static String strVal(Element ele, String name) {
		return ele.element(name).getStringValue();
	}

	private static Long longVal(Element ele, String name) {
		return Long.valueOf(ele.element(name).getStringValue());
	}

	private static Double doubleVal(Element ele, String name) {
		return Double.valueOf(ele.element(name).getStringValue());
	}

}
