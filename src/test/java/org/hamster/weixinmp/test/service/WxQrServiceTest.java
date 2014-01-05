/**
 * 
 */
package org.hamster.weixinmp.test.service;

import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.WxAuthService;
import org.hamster.weixinmp.service.WxQrService;
import org.hamster.weixinmp.test.base.AbstractWxServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 5, 2014
 *
 */
public class WxQrServiceTest extends AbstractWxServiceTest {

	@Autowired
	WxAuthService authService;
	
	@Autowired
	WxQrService qrService;
	
	@Test
	public void testAll() throws WxException {
		qrService.remoteQrcodeCreate(accessToken, true, 10000l, 1800l);
		qrService.remoteQrcodeCreate(accessToken, false, 500l, null);
	}
}
