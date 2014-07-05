/**
 * 
 */
package org.hamster.weixinmp.model.send;

import org.hamster.weixinmp.model.send.base.AbstractCustomSendJson;
import org.hamster.weixinmp.model.send.item.SendItemMusicJson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author grossopaforever@gmail.com
 * @version Dec 30, 2013
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class SendMusicJson extends AbstractCustomSendJson {
    @Override
    public String getMsgtype() {
        return "music";
    }
    
	private SendItemMusicJson music;

}
