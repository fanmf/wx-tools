/**
 * @author fanmf
 */

package com.visgirl.wx.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

/**
 * AES加密
 *
 * @author liuyazhuang
 */
public class AESUtils {

	public static boolean initialized = false;

	/**
	 * AES解密
	 *
	 * @param content 密文
	 * @return
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchProviderException
	 */
	public byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) throws InvalidAlgorithmParameterException {
		initialize();
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			Key sKeySpec = new SecretKeySpec(keyByte, "AES");

			AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("AES");
			algorithmParameters.init(new IvParameterSpec(ivByte));

			cipher.init(Cipher.DECRYPT_MODE, sKeySpec, algorithmParameters);// 初始化
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void initialize() {
		if (initialized)
			return;
		Security.addProvider(new BouncyCastleProvider());
		initialized = true;
	}

}