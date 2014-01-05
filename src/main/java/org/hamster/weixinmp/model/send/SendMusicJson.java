/**
 * 
 */
package org.hamster.weixinmp.model.send;

import org.hamster.weixinmp.model.send.item.SendItemMusicJson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author grossopaforever@gmail.com
 * @version Dec 30, 2013
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMusicJson {
	private String touser;
	private String msgtype;
	private SendItemMusicJson music;

}
