/**
 * 
 */
package org.hamster.weixinmp.config;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 29, 2013
 *
 */
@Configuration
@Data
public class WxConfig {
	private @Value("#{wxProperties.wx_token}") String token;
	private @Value("#{wxProperties.wx_appid}") String appid;
	private @Value("#{wxProperties.wx_appsecret}") String appsecret;
	private @Value("#{wxProperties.wx_menu_create_url}") String menuCreateUrl;
	private @Value("#{wxProperties.wx_access_token_create_url}") String accessTokenCreateUrl;
	
}
