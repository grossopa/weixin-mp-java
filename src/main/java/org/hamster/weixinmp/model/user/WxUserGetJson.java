/**
 * 
 */
package org.hamster.weixinmp.model.user;

import lombok.Data;

/**
 * {"total":2,"count":2,"data":{"openid":["","OPENID1","OPENID2"]},"next_openid":"NEXT_OPENID"}
 * 
 * @author grossopaforever@gmail.com
 * @version Jan 4, 2014
 *
 */
@Data
public class WxUserGetJson {
	private Long total;
	private Long count;
	WxOpenIdListJson data;
	private String next_openid;
}
