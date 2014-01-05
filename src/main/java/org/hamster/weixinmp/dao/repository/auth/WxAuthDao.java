/**
 * 
 */
package org.hamster.weixinmp.dao.repository.auth;

import org.hamster.weixinmp.dao.entity.auth.WxAuth;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author grossopaforever@gmail.com
 * @version Aug 4, 2013
 *
 */
@Repository
public interface WxAuthDao extends PagingAndSortingRepository<WxAuth, Long> {

}
