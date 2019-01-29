package github.shiyajian.pretty.utils;

import org.springframework.util.DigestUtils;

import javax.annotation.Nonnull;

/**
 * @author shiyajian
 * create: 2019-01-16
 */
public class MD5Util {

    private MD5Util() { /* no instance */ }

    /**
     * 加密
     * @param password 密码
     * @param slat     盐值
     * @return 加密后的密码
     */
    public static String getMd5(@Nonnull String password, @Nonnull String slat) {
        return DigestUtils.md5DigestAsHex(mix(password, slat).getBytes());
    }

    /**
     * 匹配输入密码和加密后密码是否一致
     * @param password        输入密码
     * @param slat            盐值
     * @param encodedPassword 已经加过密的密码
     * @return 是否匹配
     */
    public static boolean match(@Nonnull String password, @Nonnull String slat, @Nonnull String encodedPassword) {
        return encodedPassword.equals(getMd5(password, slat));
    }

    /**
     * 将密码和盐值根据一定的规律混合起来
     * @param password 密码
     * @param slat     盐值
     * @return 混合后的明文字符
     */
    private static String mix(@Nonnull String password, @Nonnull String slat) {
        return "{" + password + ")[" + slat + ")";
    }

}
