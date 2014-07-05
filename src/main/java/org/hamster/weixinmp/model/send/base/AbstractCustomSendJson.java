/**
 * 
 */
package org.hamster.weixinmp.model.send.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author <a href="mailto:grossopaforever@gmail.com">Jack Yin</a>
 * @version Jul 5, 2014 5:05:04 PM
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbstractCustomSendJson {
    private String touser;
    
    public String getMsgtype() {
        throw new RuntimeException("Not implemented");
    }
}
