package net.kdks.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 字符串处理.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return null == str || str.isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 如果给定字符串{@code str}中不包含{@code appendStr}，则在{@code str}后追加{@code appendStr}；
     * 如果已包含{@code appendStr}，则在{@code str}后追加{@code otherwise}.
     *
     * @param str       给定的字符串
     * @param appendStr 需要追加的内容
     * @param otherwise 当{@code appendStr}不满足时追加到{@code str}后的内容
     * @return 追加后的字符串
     */
    public static String appendIfNotContain(String str, String appendStr, String otherwise) {
        if (isEmpty(str) || isEmpty(appendStr)) {
            return str;
        }
        if (str.contains(appendStr)) {
            return str.concat(otherwise);
        }
        return str.concat(appendStr);
    }

    /**
     * 编码字符串.
     *
     * @param str     字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 编码后的字节码
     */
    public static byte[] bytes(CharSequence str, Charset charset) {
        if (str == null) {
            return null;
        }

        if (null == charset) {
            return str.toString().getBytes();
        }
        return str.toString().getBytes(charset);
    }

    /**
     * 解码字节码.
     *
     * @param data    字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 解码后的字符串
     */
    public static String str(byte[] data, Charset charset) {
        if (data == null) {
            return null;
        }

        if (null == charset) {
            return new String(data);
        }
        return new String(data, charset);
    }

    /**
     * map转String.
     *
     * @param params map
     * @param charset 字符集
     * @return 转换结果
     */
    public static String buildMapToStr(Map<String, Object> params, String charset) {
        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuffer data = new StringBuffer();
        boolean flag = false;

        for (Entry<String, Object> entry : params.entrySet()) {
            if (flag) {
                data.append("&");
            } else {
                flag = true;
            }
            data.append(entry.getKey()).append("=")
                .append(entry.getValue().toString());

        }
        return data.toString();
    }

    /**
     * map转String.
     *
     * @param params map
     * @param charset 字符集.
     * @return 转换结果
     */
    public static String buildMapToStrUrl(Map<String, Object> params, String charset) {

        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuffer data = new StringBuffer();
        boolean flag = false;

        for (Entry<String, Object> entry : params.entrySet()) {
            if (flag) {
                data.append("&");
            } else {
                flag = true;
            }
            try {
                data.append(entry.getKey()).append("=")
                    .append(URLEncoder.encode(entry.getValue().toString(), charset));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return data.toString();
    }

    /**
     * 字符串转换成为16进制(无需Unicode编码).
     *
     * @param bs 字符串字节数组
     * @return 16进制字符串
     */
    public static String strTo16(byte[] bs) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString().trim();
    }

}
