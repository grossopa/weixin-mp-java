/**
 * 
 */
package org.hamster.weixinmp.dao.entity.resp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemMusic;

import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
@Entity
@Table(name = "wx_resp_music")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxRespMusic extends WxBaseRespEntity {

	private WxItemMusic music;
	private Long musicId;

	@ManyToOne
	@JoinColumn(name = "music_id", nullable = false)
	public WxItemMusic getMusic() {
		return music;
	}

	@Column(name = "music_id", insertable = false, updatable = false)
	public Long getMusicId() {
		return musicId;
	}

	public void setMusic(WxItemMusic music) {
		this.music = music;
	}

	public void setMusicId(Long musicId) {
		this.musicId = musicId;
	}

}
