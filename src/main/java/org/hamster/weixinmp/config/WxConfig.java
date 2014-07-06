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
@Data
@Configuration
public class WxConfig {
	public static final String TABLE_PREFIX = "wx_";
	
	public static final int COL_LEN_URL        = 1024;
	public static final int COL_LEN_CONTENT    = 4000;
	public static final int COL_LEN_TITLE      = 200;
	public static final int COL_LEN_USER_NAME  = 100;
	public static final int COL_LEN_INDICATOR  = 64;
	
	public static final String USER_SYSTEM     = "System";
	
	private @Value("#{wxProperties.wx_token}") String token;
	private @Value("#{wxProperties.wx_appid}") String appid;
	private @Value("#{wxProperties.wx_appsecret}") String appsecret;
	
	private @Value("#{wxProperties.wx_menu_create_url}") String menuCreateUrl;
	private @Value("#{wxProperties.wx_menu_get_url}") String menuGetUrl;
	private @Value("#{wxProperties.wx_menu_delete_url}") String menuDeleteUrl;
	
	private @Value("#{wxProperties.wx_access_token_create_url}") String accessTokenCreateUrl;
	
	private @Value("#{wxProperties.wx_custom_send_url}") String customSendUrl;

	private @Value("#{wxProperties.wx_media_upload_url}") String mediaUploadUrl;
	private @Value("#{wxProperties.wx_media_download_url}") String mediaDownloadUrl;
	
	private @Value("#{wxProperties.wx_qrcode_create_url}") String qrcodeCreateUrl;
	
	private @Value("#{wxProperties.wx_user_info_url}") String userInfoUrl;
	private @Value("#{wxProperties.wx_user_get_url}") String userGetUrl;
	private @Value("#{wxProperties.wx_user_oauth_url}") String userOAuthUrl;
	private @Value("#{wxProperties.wx_user_oauth_redirect_uri}") String userOAuthRedirectUri;
	private @Value("#{wxProperties.wx_user_oauth_access_token_url}") String userOAuthAccessTokenUrl;
	private @Value("#{wxProperties.wx_user_oauth_refresh_token_url}") String userOAuthRefreshTokenUrl;
	private @Value("#{wxProperties.wx_user_oauth_user_info_url}") String userOAuthUserInfoUrl;
	
	private @Value("#{wxProperties.wx_groups_create_url}") String groupsCreateUrl;
	private @Value("#{wxProperties.wx_groups_get_url}") String groupsGetUrl;
	private @Value("#{wxProperties.wx_groups_getid_url}") String groupsGetIdUrl;
	private @Value("#{wxProperties.wx_groups_update_url}") String groupsUpdateUrl;
	private @Value("#{wxProperties.wx_groups_members_update_url}") String groupsMembersUpdateUrl;
	
	
	
}
