/**
 * 
 */
package org.hamster.weixinmp.test.service;

import java.util.List;

import org.hamster.weixinmp.dao.entity.user.WxGroupEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.WxAuthService;
import org.hamster.weixinmp.service.WxUserGroupService;
import org.hamster.weixinmp.test.base.AbstractWxServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 1, 2014
 *
 */
public class WxGroupServceTest extends AbstractWxServiceTest {

	@Autowired
	WxAuthService authService;
	
	@Autowired
	WxUserGroupService userGroupService;
	
	@Test
	public void testAll() throws WxException {
		WxGroupEntity newgroup = userGroupService.remoteGroupsCreate(accessToken, "API测试组");
		List<WxGroupEntity> groups = userGroupService.remoteGroupsGet(accessToken);
		
		boolean found = false;
		for (WxGroupEntity group : groups) {
			if (group.getName().equals(newgroup.getName())) {
				found = true;
			}
		}
		if (found == false) {
			throw new WxException("not found newly created group!"); 
		}
		
		userGroupService.remoteGroupsUpdate(accessToken, newgroup.getWxId(), "API测试组2");
	}
}
