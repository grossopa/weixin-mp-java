/**
 * 
 */
package org.hamster.weixinmp.dao.repository.msg;

import org.hamster.weixinmp.dao.entity.msg.WxMsgEvent;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 *
 */
public interface WxMsgEventDao extends PagingAndSortingRepository<WxMsgEvent, Long> {

}
