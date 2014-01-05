/**
 * 
 */
package org.hamster.weixinmp.model.send.item.wrapper;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hamster.weixinmp.model.send.item.SendItemArticleJson;

/**
 * @author grossopaforever@gmail.com
 * @version Dec 30, 2013
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxSendItemArticleWrapper {

	private List<SendItemArticleJson> articles;
}
