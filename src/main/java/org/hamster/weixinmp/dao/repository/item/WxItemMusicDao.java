/**
 * 
 */
package org.hamster.weixinmp.dao.repository.item;

import org.hamster.weixinmp.dao.entity.item.WxItemMusicEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemVoiceEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 *
 */
@Repository
public interface WxItemMusicDao extends PagingAndSortingRepository<WxItemMusicEntity, Long> {

    /**
     * @param musicurl
     * @return
     */
    WxItemVoiceEntity findByMusicUrl(String musicurl);

}
