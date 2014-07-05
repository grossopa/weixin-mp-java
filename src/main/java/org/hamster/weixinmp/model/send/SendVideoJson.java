/**
 * 
 */
package org.hamster.weixinmp.model.send;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hamster.weixinmp.model.send.base.AbstractCustomSendJson;
import org.hamster.weixinmp.model.send.item.SendItemVideoJson;

/**
 * @author grossopaforever@gmail.com
 * @version Dec 30, 2013
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class SendVideoJson extends AbstractCustomSendJson {
    @Override
    public String getMsgtype() {
        return "video";
    }
	private SendItemVideoJson video;
}
