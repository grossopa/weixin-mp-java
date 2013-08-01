/**
 * 
 */
package org.hamster.weixinmp.dao.repository.auth;

import org.hamster.weixinmp.dao.entity.auth.WxAuthReq;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 *
 */
public interface WxAuthReqDao extends PagingAndSortingRepository<WxAuthReq, Long> {

}
