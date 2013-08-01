/**
 * 
 */
package org.hamster.weixinmp.dao.entity.resp;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemPicDesc;

import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Entity
@Table(name = "wx_resp_pic_desc")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxRespPicDesc extends WxBaseRespEntity {

	private List<WxItemPicDesc> articles;

	@ManyToMany
	@JoinTable(name = "wx_resp_pic_desc_item")
	public List<WxItemPicDesc> getArticles() {
		return articles;
	}

	public void setArticles(List<WxItemPicDesc> articles) {
		this.articles = articles;
	}

}
