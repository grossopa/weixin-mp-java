/**
 * 
 */
package org.hamster.weixinmp.controller.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hamster.weixinmp.constant.WxReqTypeEnum;
import org.hamster.weixinmp.dao.entity.auth.WxAuthReq;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemPicDesc;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEvent;
import org.hamster.weixinmp.dao.entity.msg.WxMsgImg;
import org.hamster.weixinmp.dao.entity.msg.WxMsgLink;
import org.hamster.weixinmp.dao.entity.msg.WxMsgLoc;
import org.hamster.weixinmp.dao.entity.msg.WxMsgText;
import org.hamster.weixinmp.dao.entity.resp.WxRespMusic;
import org.hamster.weixinmp.dao.entity.resp.WxRespPicDesc;
import org.hamster.weixinmp.dao.entity.resp.WxRespText;


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
	 * 
	 * @param xmlstr
	 * @return
	 * @throws DocumentException
	 */
	public static WxMsgText getMsgText(Element ele) throws DocumentException {
		WxMsgText result = msgEntityFactory(WxMsgText.class, ele);
		result.setMsgId(longVal(ele, "MsgId"));
		result.setContent(strVal(ele, "Content"));
		return result;
	}
	
	/**
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;1348831860&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[image]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;PicUrl&gt;&lt;![CDATA[this is a url]]&gt;&lt;/PicUrl&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;<br />
	 * &lt;/xml&gt;
	 * 
	 * @param xmlstr
	 * @return
	 * @throws DocumentException
	 */
	public static WxMsgImg getMsgImg(Element ele) throws DocumentException {
		WxMsgImg result = msgEntityFactory(WxMsgImg.class, ele);
		result.setPicUrl(strVal(ele, "PicUrl"));
		return result;
	}
	
	/**
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
	 * 
	 * 
	 * @param xmlstr
	 * @return
	 * @throws DocumentException
	 */
	public static WxMsgLoc getMsgLoc(Element ele) throws DocumentException {
		WxMsgLoc result = msgEntityFactory(WxMsgLoc.class, ele);
		result.setLabel(strVal(ele, "Label"));
		result.setLocationX(doubleVal(ele, "Location_X"));
		result.setLocationY(doubleVal(ele, "Location_Y"));
		result.setScale(doubleVal(ele, "Scale"));
		return result;
	}

	/**
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
	 * 
	 * @param xmlstr
	 * @return
	 * @throws DocumentException
	 */
	public static WxMsgLink getMsgLink(Element ele) throws DocumentException {
		WxMsgLink result = msgEntityFactory(WxMsgLink.class, ele);
		result.setTitle(strVal(ele, "Title"));
		result.setDescription(strVal(ele, "Description"));
		result.setUrl(strVal(ele, "Url"));
		return result;
	}
	
	/**
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[FromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;123456789&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[event]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Event&gt;&lt;![CDATA[EVENT]]&gt;&lt;/Event&gt;<br />
	 * &nbsp;&nbsp;&lt;EventKey&gt;&lt;![CDATA[EVENTKEY]]&gt;&lt;/EventKey&gt;<br />
	 * &lt;/xml&gt;
	 * 
	 * @param xmlstr
	 * @return
	 * @throws DocumentException
	 */
	public static WxMsgEvent getMsgEvent(Element ele) throws DocumentException {
		WxMsgEvent result = msgEntityFactory(WxMsgEvent.class, ele);
		result.setEvent(strVal(ele, "Event"));
		result.setEventKey(strVal(ele, "EventKey"));
		return result;
	}
	
	/**
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[text]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Content&gt;&lt;![CDATA[content]]&gt;&lt;/Content&gt;<br />
	 * &nbsp;&nbsp;&lt;FuncFlag&gt;0&lt;/FuncFlag&gt;<br />
	 * &lt;/xml&gt;
	 * 
	 * @param respText
	 * @return
	 * @throws DocumentException
	 */
	public static Element getRespTextXML(WxRespText respText) throws DocumentException {
		Element ele = respEntityFactory(respText);
		ele.addElement("Content").addCDATA(respText.getContent());
		return ele;
	}
	
	/**
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
	 * &nbsp;&nbsp;&lt;/Music&gt;<br />
	 * &nbsp;&nbsp;&lt;FuncFlag&gt;0&lt;/FuncFlag&gt;<br />
	 * &lt;/xml&gt;
	 * 
	 * @param respMusic
	 * @return
	 * @throws DocumentException
	 */
	public static Element getRespMusic(WxRespMusic respMusic) throws DocumentException {
		Element ele = respEntityFactory(respMusic);
		Element musicEle = ele.addElement("Music");
		musicEle.addElement("Title").addCDATA(respMusic.getMusic().getTitle());
		musicEle.addElement("Description").addCDATA(respMusic.getMusic().getDescription());
		musicEle.addElement("MusicUrl").addCDATA(respMusic.getMusic().getMusicUrl());
		musicEle.addElement("HQMusicUrl").addCDATA(respMusic.getMusic().getHqMusicUrl());
		return ele;
	}
	
	/**
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
	 * 
	 * @param respPicDesc
	 * @return
	 * @throws DocumentException
	 */
	public static Element getRespPicDesc(WxRespPicDesc respPicDesc) throws DocumentException {
		Element ele = respEntityFactory(respPicDesc);
		ele.addElement("ArticleCount").addText(String.valueOf(respPicDesc.getArticles().size()));
		Element articlesEle = ele.addElement("Articles");
		for (WxItemPicDesc item : respPicDesc.getArticles()) {
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
	
	public static WxReqTypeEnum getReqType(Element ele) {
		String type = ele.element("MsgType").getTextTrim();
		return WxReqTypeEnum.inst(type);
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
		ele.addElement("CreateTime").setText(String.valueOf(entity.getCreateTime()));
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
