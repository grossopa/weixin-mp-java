/**
 * 
 */
package org.hamster.weixinmp.dao.repository.auth;

import org.hamster.weixinmp.dao.entity.auth.WxAuth;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author grossopaforever@gmail.com
 * @version Aug 4, 2013
 *
 */
public interface WxAuthDao extends PagingAndSortingRepository<WxAuth, Long> {

}
