/**
 * 
 */
package org.hamster.weixinmp.dao.entity.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.base.WxBaseItemMediaEntity;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "wx_item_pic_desc")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxItemPicDescEntity extends WxBaseItemMediaEntity {
    @Column(name = "title", length = WxConfig.COL_LEN_TITLE, nullable = false)
    private String title;
    @Column(name = "description", length = WxConfig.COL_LEN_CONTENT, nullable = false)
    private String description;
    @Column(name = "pic_url", length = WxConfig.COL_LEN_URL, nullable = false)
    private String picUrl;
    @Column(name = "url", length = WxConfig.COL_LEN_URL, nullable = false)
    private String url;

    //////////
    // Mass //
    //////////
    @Column(name = "is_mass_item", nullable = true)
    // see http://mp.weixin.qq.com/wiki/index.php?title=%E9%AB%98%E7%BA%A7%E7%BE%A4%E5%8F%91%E6%8E%A5%E5%8F%A3
    private Boolean isMassItem;
    @Column(name = "author", length = WxConfig.COL_LEN_TITLE, nullable = true)
    // for mass item
    private String author;
    @Column(name = "digest", length = WxConfig.COL_LEN_TITLE, nullable = true)
    private String digest;
    @Column(name = "show_cover_pic", nullable = true)
    private Integer showCoverPic;
    @Column(name = "content_source_url", nullable = true)
    private String contentSourceUrl;
    @Column(name = "thumb_media_id", length = WxConfig.COL_LEN_INDICATOR, nullable = true)
    private String thumbMediaId;
    /////////////////
    // End of Mass //
    /////////////////
}