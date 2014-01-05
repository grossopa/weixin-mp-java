/**
 * 
 */
package org.hamster.weixinmp.dao.repository.item;

import org.hamster.weixinmp.dao.entity.item.WxItemVideoEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 5, 2014
 * 
 */
@Repository
public interface WxItemVideoDao extends
		PagingAndSortingRepository<WxItemVideoEntity, Long> {

}
