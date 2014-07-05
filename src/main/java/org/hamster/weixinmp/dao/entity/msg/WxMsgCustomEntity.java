/**
 * 
 */
package org.hamster.weixinmp.dao.entity.msg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.base.WxBaseEntity;

/**
 *
 * @author <a href="mailto:grossopaforever@gmail.com">Jack Yin</a>
 * @version Jul 5, 2014 9:22:21 PM
 *
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "msg_custom")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxMsgCustomEntity extends WxBaseEntity {
    @Column(name = "json", length = 5000, nullable = false)
    private String json;
    @Column(name = "result", length = 1000, nullable = false)
    private String result;
    @Column(name = "type", length = 20, nullable = false)
    private String type;
    @Column(name = "toUser", length = WxConfig.COL_LEN_USER_NAME, nullable = false)
    private String toUser;
}
