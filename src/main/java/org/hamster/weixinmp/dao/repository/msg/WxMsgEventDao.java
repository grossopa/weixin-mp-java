/**
 * 
 */
package org.hamster.weixinmp.dao.repository.msg;

import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 *
 */
@Repository
public interface WxMsgEventDao extends PagingAndSortingRepository<WxMsgEventEntity, Long> {

}
