/**
 * 
 */
package org.hamster.weixinmp.controller;

import lombok.Setter;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.hamster.weixinmp.constant.WxReqTypeEnum;
import org.hamster.weixinmp.controller.util.WxXmlUtil;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespMusic;
import org.hamster.weixinmp.dao.entity.resp.WxRespPicDesc;
import org.hamster.weixinmp.dao.entity.resp.WxRespText;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Controller
@RequestMapping("/rest/weixinmp")
public class WxController {
	private static final Logger log = Logger.getLogger(WxController.class);
	
	@Autowired
	@Setter
	private WxService wxService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	String authGet(@RequestParam("signature") String signature,
			@RequestParam("timestamp") String timestamp,
			@RequestParam("nonce") String nonce,
			@RequestParam("echostr") String echostr) throws WxException {
		if (wxService.validateAuth(signature, timestamp, nonce, echostr)) {
			log.info("received authentication message from Weixin Server.");
			return echostr;
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	String post(@RequestBody String requestBody) throws DocumentException {
		Element ele = WxXmlUtil.toXML(requestBody);
		WxReqTypeEnum type = WxXmlUtil.getReqType(ele);
		log.info("received " + type + " message.");
		WxBaseMsgEntity msg = null;
		switch (type) {
		case TEXT:
			msg = wxService.saveMsgText(ele);
			break;
		case IMAGE:
			msg = wxService.saveMsgImg(ele);
			break;
		case LOCATION:
			msg = wxService.saveMsgLoc(ele);
			break;
		case LINK:
			msg = wxService.saveMsgLink(ele);
			break;
		case EVENT:
			msg = wxService.saveMsgEvent(ele);
			break;
		}
		WxBaseRespEntity resp = wxService.handleMessage(msg);
		if (resp instanceof WxRespText) {
			return WxXmlUtil.getRespTextXML((WxRespText)resp).asXML();
		} else if (resp instanceof WxRespPicDesc) {
			return WxXmlUtil.getRespPicDesc((WxRespPicDesc)resp).asXML();
		} else if (resp instanceof WxRespMusic) {
			return WxXmlUtil.getRespMusic((WxRespMusic)resp).asXML();
		}
		return "";
	}

}
