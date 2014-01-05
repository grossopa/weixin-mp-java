/**
 * 
 */
package org.hamster.weixinmp.dao.repository.user;

import org.hamster.weixinmp.dao.entity.user.WxUserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 *
 *
 */
@Repository
public interface WxUserDao extends PagingAndSortingRepository<WxUserEntity, Long> {
}
