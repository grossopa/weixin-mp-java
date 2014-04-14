/**
 * 
 */
package org.hamster.weixinmp.test.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.auth.WxAuth;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.WxAuthService;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 5, 2014
 * 
 */
public abstract class AbstractWxServiceTest extends AbstractServiceTest {
	
	public static final String TEST_FOLDER = "src/test/resources/tmp";
	public static final String ACCESS_TOKEN_FILE = TEST_FOLDER + "/accessToken.txt";

	
	protected String accessToken;
	
	@Autowired
	WxConfig config;
	
	@Autowired
	WxAuthService authService;
	
	@Before
	public void setUp() throws WxException, IOException {
		if (StringUtils.isBlank(accessToken)) {
			File testConfigFile = new File(ACCESS_TOKEN_FILE);
			Date currentDate = new Date();
			if (!testConfigFile.exists() || currentDate.getTime() - testConfigFile.lastModified() > 86400000) {
				WxAuth auth = authService.getAccessToken(config.getAppid(), config.getAppsecret());
				this.accessToken = auth.getAccessToken();
				new File(TEST_FOLDER).mkdirs();
				FileWriter writer = new FileWriter(ACCESS_TOKEN_FILE);
				writer.write(accessToken);
				writer.flush();
				writer.close();
			} else {
				BufferedReader reader = new BufferedReader(new FileReader(ACCESS_TOKEN_FILE));
				this.accessToken = reader.readLine();
				reader.close();
			}
		}
	}
}
