package com.visgirl.wx.utils;

import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

/**
 * @author fanmf
 */
public class Demo {

	private static final String WATERMARK = "watermark";
	private static final String APPID = "appid";

	/**
	 * 解密数据
	 *
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String appId, String encryptedData, String sessionKey, String iv) {
		String result = "";
		try {
			AESUtils aes = new AESUtils();
			byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey),
					Base64.decodeBase64(iv));
			if (null != resultByte && resultByte.length > 0) {
				result = new String(PKCS7Utils.decode(resultByte));
				JSONObject jsonObject = JSONObject.fromObject(result);
				String decryptAppid = jsonObject.getJSONObject(WATERMARK).getString(APPID);
				if (!appId.equals(decryptAppid)) {
					result = "";
				}
			}
		} catch (Exception e) {
			result = "";
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		String appId = "wxe8cffa7fe02b0627";
		String encryptedData = "JsRye//wWYom06IMLsi6ClfvKl6Zw9Z4qyS0AOv2ky2JTY8cpvabTaW2qkQn2TyN5RF" +
				"/TDpUi3T8sCFfe0uljTZquG24kr5Z472c94zAKRGvlhl5jUYhEqCejUmpPajE4" +
				"/NsI9igDCJ1Bn9slXxUjxJuEtpMS6YYp3IG8kgNT7LUlpfiJJbCd2pVGZuLWaqweYQT4i7ue7+eP0ats86YqqDwACPD8gVIiNc" +
				"/3oVMN" +
				"/PWUwrL19wOpKC26BFL4CM79Wkw5EJhrEM7i08K1tyUQSvK34quraZaFWS8wMZZqTTvhaLAXpB8xqFzdsBl6FDsOUb6oqDhOAQJZ9wHfrHw+JvuSU+Nk+I5cvlXdOqtcSlyXor9otoLf9pVU0Z8S620YWK+/2hUli8f+HAssWa9OayR4TA5HlhsZojlgI2Jzp84UkLRbKinyRXTG0LoIe/fbh9Wkj1BmyMk5GLuFdRghg=='\n" +
				"var iv = 'mHPsUCR56Vf6R7VfX38YCw==";
		String sessionKey = "nYDUowcexcW5UYPsADg8sQ==";
		String iv = "mHPsUCR56Vf6R7VfX38YCw==";
		System.out.println(decrypt(appId, encryptedData, sessionKey, iv));
	}

}
