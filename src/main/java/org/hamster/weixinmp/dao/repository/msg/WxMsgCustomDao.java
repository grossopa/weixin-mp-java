/**
 * 
 */
package org.hamster.weixinmp.dao.repository.msg;

import org.hamster.weixinmp.dao.entity.msg.WxMsgCustomEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author <a href="mailto:grossopaforever@gmail.com">Jack Yin</a>
 * @version Jul 5, 2014 9:31:10 PM
 *
 */
@Repository
public interface WxMsgCustomDao extends PagingAndSortingRepository<WxMsgCustomEntity, Long> {
}
