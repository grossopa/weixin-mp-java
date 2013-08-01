/**
 * 
 */
package org.hamster.weixinmp.util;

import java.util.Date;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 *
 */
public class WxUtil {

	private WxUtil() { }
	
	public static final Long currentTimeInSec() {
		return Long.valueOf(new Date().getTime() / 1000);
	}

}
