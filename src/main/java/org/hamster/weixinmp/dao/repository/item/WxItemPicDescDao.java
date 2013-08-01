/**
 * 
 */
package org.hamster.weixinmp.dao.repository.item;

import java.util.List;

import org.hamster.weixinmp.dao.entity.item.WxItemPicDesc;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 *
 */
public interface WxItemPicDescDao extends PagingAndSortingRepository<WxItemPicDesc, Long> {
	/**
	 * 
	 * @param ids
	 * @return
	 */
	List<WxItemPicDesc> findByIdIn(List<Long> ids);
}
