package net.kdks.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public class DigestUtils {
    /**
     * MD5 加密.
     *
     * @param str 待加密字符串
     * @return 加密结果
     */
    public static byte[] md5Digest(String str) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * MD5 加密.
     *
     * @param str 待加密字符串
     * @return 加密结果
     */
    public static String md5DigestToStr(String str) {
        StringBuffer result = new StringBuffer();
        byte[] bytes = md5Digest(str);
        for (byte b : bytes) {
            int bt = b & 0xff;
            if (bt < 16) {
                result.append(0);
            }
            result.append(Integer.toHexString(bt));
        }
        return result.toString();
    }
}
