package com.tpnet.tpbluetooth.util;

import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 *
 */
public class AESUtils {
    public static final String TAG = "AESUtils";
    /**
     * 解密使用的key
     */
    public static String AES_KEY = "ei20 2202 0612 1212";

    /**
     * AES加密
     *
     * @param data 将要加密的内容
     * @param key  密钥
     * @return 已经加密的内容
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        byte[] newkey = add(key);
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(newkey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    /**
     * AES解密
     *
     * @param data 将要解密的内容
     * @param key  密钥
     * @return 已经解密的内容
     */

    public static byte[] decrypt(byte[] data, byte[] key) {
        byte[] newkey = add(key);
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(newkey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] decryptData = cipher.doFinal(data);
            return decryptData;
        } catch (Exception e) {
            Log.e(TAG, "error:" + e.getMessage());
            e.printStackTrace();
        }
        return new byte[]{};
    }


    //zeropadding
    private static byte[] add(byte[] oldbyte) {
        int len = 16 - oldbyte.length % 16;
        if (len == 0)
            return oldbyte;
        byte[] newByte = new byte[oldbyte.length + len];
        System.arraycopy(oldbyte, 0, newByte, 0, oldbyte.length);
        return newByte;
    }

}
