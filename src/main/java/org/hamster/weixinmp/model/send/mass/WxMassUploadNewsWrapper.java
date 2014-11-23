/**
 * 
 */
package org.hamster.weixinmp.model.send.mass;

import java.util.List;

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
public class WxMassUploadNewsWrapper {

	private List<SendMassArticleJson> articles;
}
