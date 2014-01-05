/**
 * 
 */
package org.hamster.weixinmp.model.send;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hamster.weixinmp.model.send.item.SendItemVideoJson;

/**
 * @author grossopaforever@gmail.com
 * @version Dec 30, 2013
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendVoiceJson {
	private String touser;
	private String msgtype;
	private SendItemVideoJson video;
}
