/**
 * 
 */
package org.hamster.weixinmp.dao.repository.msg;

import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author grossopaforever@gmail.com
 * @version Dec 30, 2013
 *
 */
@Repository
public interface WxBaseMsgDao extends PagingAndSortingRepository<WxBaseMsgEntity, Long> {

}
