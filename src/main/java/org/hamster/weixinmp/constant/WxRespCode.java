/**
 * 
 */
package org.hamster.weixinmp.constant;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author grossopaforever@gmail.com
 * @version Aug 1, 2013
 * 
 */
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class WxRespCode {

	@Getter
	private Integer code;
	@Getter
	private String desc;

}
