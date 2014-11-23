/**
 * 
 */
package org.hamster.weixinmp.dao.entity.msg.mass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.base.WxBaseEntity;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author <a href="mailto:grossopaforever@gmail.com">Jack Yin</a>
 * @version Nov 8, 2014 3:34:42 PM
 *
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "msg_mass_sent_item")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxMsgMassSentItemEntity extends WxBaseEntity {
    
    @Column(name="type", length = WxConfig.COL_LEN_INDICATOR, nullable = false)
    private String type;
    @SerializedName("media_id")
    @Column(name="mediaId", length = WxConfig.COL_LEN_INDICATOR, nullable = false)
    private String mediaId;
    @SerializedName("created_at")
    @Column(name="createdAt", nullable = false)
    private Long createdAt;
    @Column(name="item_id", nullable = false)
    private Long itemId;
}
