/**
 * 
 */
package org.hamster.weixinmp.test.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.hamster.weixinmp.constant.WxMediaTypeEnum;
import org.hamster.weixinmp.dao.entity.item.WxItemImageEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.WxAuthService;
import org.hamster.weixinmp.service.WxMediaService;
import org.hamster.weixinmp.test.base.AbstractWxServiceTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 4, 2014
 * 
 */
public class WxMediaServiceTest extends AbstractWxServiceTest {

	@Autowired
	WxAuthService authService;

	@Autowired
	WxMediaService mediaService;

	@Test
	@Ignore
	public void testMedia() throws WxException, FileNotFoundException, IOException {
		File imageFile = new File("src/test/resources/media/cat.jpg");
		byte[] content = IOUtils.toByteArray(new FileReader(imageFile));
		WxItemImageEntity imageMedia = (WxItemImageEntity) mediaService
				.remoteMediaUpload(accessToken, WxMediaTypeEnum.IMAGE, content);
		System.out.println(imageMedia);
	}
}
