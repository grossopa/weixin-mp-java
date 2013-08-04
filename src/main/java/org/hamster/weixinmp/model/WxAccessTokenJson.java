/**
 * 
 */
package org.hamster.weixinmp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author grossopaforever@gmail.com
 * @version Aug 4, 2013
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxAccessTokenJson {

	private String access_token;
	private String expires_in;

}
