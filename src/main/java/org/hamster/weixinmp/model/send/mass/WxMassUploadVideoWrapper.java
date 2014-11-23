/**
 * 
 */
package org.hamster.weixinmp.model.send.mass;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author <a href="mailto:grossopaforever@gmail.com">Jack Yin</a>
 * @version Nov 23, 2014 10:09:34 AM
 *
 */
@Data
@AllArgsConstructor
public class WxMassUploadVideoWrapper {
    private String media_id;
    private String title;
    private String description;
}
