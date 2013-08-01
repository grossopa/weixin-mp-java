/**
 * 
 */
package org.hamster.weixinmp.test.service;

import org.dom4j.DocumentException;
import org.hamster.weixinmp.controller.util.WxXmlUtil;
import org.hamster.weixinmp.dao.entity.resp.WxRespText;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.WxService;
import org.hamster.weixinmp.test.base.AbstractServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
public class WxServiceTest extends AbstractServiceTest {

	@Autowired
	WxService wxService;

	@Test
	public void testValidateAuth() throws WxException {
		wxService.setToken("HamsterNice123ILove");
		Assert.assertTrue(wxService.validateAuth(
				"9d31490b4386ad3bb9bbb8ac5150fb3e6230c171",
				"1375112572", "1375102247",
				"5906019193781128573"));
	}
	
	@Test
	public void testCreateRespText() throws DocumentException {
		WxRespText respText = wxService.createRespText("this is a content", "foo", "bar", 0);
		WxXmlUtil.getRespTextXML(respText);
	}

}
