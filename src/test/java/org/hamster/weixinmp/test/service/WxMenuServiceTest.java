/**
 * 
 */
package org.hamster.weixinmp.test.service;

import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.model.menu.WxMenuCreateJson;
import org.hamster.weixinmp.service.WxAuthService;
import org.hamster.weixinmp.service.WxMenuService;
import org.hamster.weixinmp.test.base.AbstractWxServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 4, 2014
 * 
 */
public class WxMenuServiceTest extends AbstractWxServiceTest {

	public static final String MENU_CREATE_JSON = " {" + "\"button\":[" + "{	"
			+ "    \"type\":\"click\"," + "   \"name\":\"今日歌曲\","
			+ "  \"key\":\"V1001_TODAY_MUSIC\"" + "}," + "{"
			+ "    \"type\":\"click\"," + "   \"name\":\"歌手简介\","
			+ "   \"key\":\"V1001_TODAY_SINGER\"" + "  }," + "  {"
			+ "       \"name\":\"菜单\"," + "       \"sub_button\":["
			+ "      {	" + "          \"type\":\"view\","
			+ "         \"name\":\"搜索\","
			+ "        \"url\":\"http://www.soso.com/\"" + "    }," + "   {"
			+ "     \"type\":\"view\"," + "    \"name\":\"视频\","
			+ "   \"url\":\"http://v.qq.com/\"" + "    }," + "   {"
			+ "     \"type\":\"click\"," + "    \"name\":\"赞一下我们\","
			+ "   \"key\":\"V1001_GOOD\"" + " }]" + "}]" + "}";

	@Autowired
	WxAuthService authService;
	@Autowired
	WxMenuService menuService;

	@Test
	public void testAll() throws WxException {
		Gson gson = new Gson();
		WxMenuCreateJson dummyJson = gson.fromJson(MENU_CREATE_JSON,
				WxMenuCreateJson.class);

		menuService.menuCreate(accessToken, dummyJson.getButton());

		System.out.println(menuService.menuGet(accessToken));

		menuService.menuDelete(accessToken);
	}

}
