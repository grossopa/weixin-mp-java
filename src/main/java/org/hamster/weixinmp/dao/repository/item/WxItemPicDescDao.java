/**
 * 
 */
package org.hamster.weixinmp.dao.repository.item;

import java.util.List;

import org.hamster.weixinmp.dao.entity.item.WxItemPicDescEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 *
 */
@Repository
public interface WxItemPicDescDao extends PagingAndSortingRepository<WxItemPicDescEntity, Long> {
	/**
	 * 
	 * @param ids
	 * @return
	 */
	List<WxItemPicDescEntity> findByIdIn(List<Long> ids);
}
