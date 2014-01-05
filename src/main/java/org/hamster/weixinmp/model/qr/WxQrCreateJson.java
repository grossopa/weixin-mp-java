/**
 * 
 */
package org.hamster.weixinmp.model.qr;

import lombok.Data;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 4, 2014
 *
 */
@Data
public class WxQrCreateJson {
	private String action_name;
	private Long expire_seconds;
	private WxQrActionInfoJson action_info;
}
