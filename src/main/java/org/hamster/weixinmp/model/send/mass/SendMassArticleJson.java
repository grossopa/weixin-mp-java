/**
 * 
 */
package org.hamster.weixinmp.model.send.mass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author <a href="mailto:grossopaforever@gmail.com">Jack Yin</a>
 * @version Nov 8, 2014 4:02:14 PM
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMassArticleJson {
    private String thumb_media_id;
    private String author;
    private String title;
    private String content_source_url;
    private String content;
    private String digest;
    private Integer show_cover_pic;
}
