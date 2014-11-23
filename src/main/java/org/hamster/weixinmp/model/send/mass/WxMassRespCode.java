/**
 * 
 */
package org.hamster.weixinmp.model.send.mass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hamster.weixinmp.model.WxRespCode;

/**
 *
 * @author <a href="mailto:grossopaforever@gmail.com">Jack Yin</a>
 * @version Nov 23, 2014 11:28:26 AM
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WxMassRespCode extends WxRespCode {
    private String msg_id;
}
