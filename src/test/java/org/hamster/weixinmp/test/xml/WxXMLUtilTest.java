/**
 * 
 */
package org.hamster.weixinmp.test.xml;

import org.dom4j.DocumentException;
import org.hamster.weixinmp.controller.util.WxXmlUtil;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgImageEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgLinkEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgLocEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgTextEntity;
import org.junit.Assert;
import org.junit.Test;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
public class WxXMLUtilTest {

	public static final String MSG_TEXT_XML = "<xml>"
			+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
			+ "<FromUserName><![CDATA[fromUser]]></FromUserName> "
			+ "<CreateTime>1348831860</CreateTime>"
			+ "<MsgType><![CDATA[text]]></MsgType>"
			+ "<Content><![CDATA[this is a test]]></Content>"
			+ "<MsgId>1234567890123456</MsgId>" + "</xml>";
	
	@Test
	public void testGetMsgText() throws DocumentException {
		WxMsgTextEntity msgText = WxXmlUtil.getMsgText(WxXmlUtil.toXML(MSG_TEXT_XML));
		assertBaseFieldsWithMsgId(msgText);
		Assert.assertEquals("this is a test", msgText.getContent());
	}
	
	public static final String MSG_IMG_XML = "<xml>"
			+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
			+ "<FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>1348831860</CreateTime>"
			+ "<MsgType><![CDATA[image]]></MsgType>"
			+ "<PicUrl><![CDATA[this is a url]]></PicUrl>"
			+ "<MediaId><![CDATA[media_id]]></MediaId>"
			+ "<MsgId>1234567890123456</MsgId>" + "</xml>";
	
	@Test
	public void testGetMsgImg() throws DocumentException {
		WxMsgImageEntity msgImg = WxXmlUtil.getMsgImage(WxXmlUtil.toXML(MSG_IMG_XML));
		assertBaseFieldsWithMsgId(msgImg);
		//Assert.assertEquals("this is a url", msgImg.getImage().getPicUrl());
	}
	
	public static final String MSG_LOC_XML = "<xml>"
			+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
			+ "<FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>1351776360</CreateTime>"
			+ "<MsgType><![CDATA[location]]></MsgType>"
			+ "<Location_X>23.134521</Location_X>"
			+ "<Location_Y>113.358803</Location_Y>" 
			+ "<Scale>20</Scale>"
			+ "<Label><![CDATA[位置信息]]></Label>"
			+ "<MsgId>1234567890123456</MsgId>" + "</xml> ";
	
	@Test
	public void testGetMsgLoc() throws DocumentException {
		WxMsgLocEntity msgLoc = WxXmlUtil.getMsgLoc(WxXmlUtil.toXML(MSG_LOC_XML));
		assertBaseFieldsWithMsgId(msgLoc);
		Assert.assertEquals(Double.valueOf(23.134521d), msgLoc.getLocationX());
		Assert.assertEquals(Double.valueOf(113.358803d), msgLoc.getLocationY());
		Assert.assertEquals(Double.valueOf(20), msgLoc.getScale());
		Assert.assertEquals("位置信息", msgLoc.getLabel());
	}
	
	public static final String MSG_LINK_XML = "<xml>"
			+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
			+ "<FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>1351776360</CreateTime>"
			+ "<MsgType><![CDATA[link]]></MsgType>"
			+ "<Title><![CDATA[公众平台官网链接]]></Title>"
			+ "<Description><![CDATA[公众平台官网链接123]]></Description>"
			+ "<Url><![CDATA[url]]></Url>" + "<MsgId>1234567890123456</MsgId>"
			+ "</xml> ";
	
	@Test
	public void testGetMsgLink() throws DocumentException {
		WxMsgLinkEntity msgLink = WxXmlUtil.getMsgLink(WxXmlUtil.toXML(MSG_LINK_XML));
		assertBaseFieldsWithMsgId(msgLink);
		Assert.assertEquals("公众平台官网链接", msgLink.getTitle());
		Assert.assertEquals("公众平台官网链接123", msgLink.getDescription());
		Assert.assertEquals("url", msgLink.getUrl());
	}
	
	public static final String MSG_EVENT_XML = "<xml>"
			+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
			+ "<FromUserName><![CDATA[FromUser]]></FromUserName>"
			+ "<CreateTime>123456789</CreateTime>"
			+ "<MsgType><![CDATA[event]]></MsgType>"
			+ "<Event><![CDATA[EVENT]]></Event>"
			+ "<EventKey><![CDATA[EVENTKEY]]></EventKey>" + "</xml>";
	
	@Test
	public void testGetMsgEvent() throws DocumentException {
		WxMsgEventEntity msgEvent = WxXmlUtil.getMsgEvent(WxXmlUtil.toXML(MSG_EVENT_XML));
		assertBaseFields(msgEvent);
		Assert.assertEquals("EVENT", msgEvent.getEvent());
		Assert.assertEquals("EVENTKEY", msgEvent.getEventKey());
	}
	
	private void assertBaseFields(WxBaseMsgEntity entity) {
		Assert.assertNotNull(entity.getFromUserName()); 
		Assert.assertNotNull(entity.getToUserName()); 
		Assert.assertNotNull(entity.getCreateTime()); 
		Assert.assertNotNull(entity.getMsgType()); 
	}
	
	private void assertBaseFieldsWithMsgId(WxBaseMsgEntity entity) {
		assertBaseFields(entity);
		Assert.assertNotNull(entity.getMsgId()); 
	}

}
