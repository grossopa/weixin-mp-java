/**
 * 
 */
package org.hamster.weixinmp.dao.entity.base;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author grossopaforever@gmail.com
 * @version Dec 29, 2013
 * 
 */

@MappedSuperclass
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxBaseItemMediaEntity extends WxBaseEntity {
}
