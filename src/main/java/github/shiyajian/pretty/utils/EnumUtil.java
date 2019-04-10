package github.shiyajian.pretty.utils;

import github.shiyajian.pretty.commons.Enumerable;

import javax.annotation.Nonnull;

/**
 * 枚举工具类
 * @author shiyajian
 * create: 2019-01-27
 */
public final class EnumUtil {

    private EnumUtil() { /* no instance */ }

    /**
     * 在类中查找对应的值
     * @param classType 枚举类
     * @param value     value值
     * @param <E>       必须实现 Enumerable 接口
     * @return 枚举值
     */
    public static <E extends Enumerable> E of(@Nonnull Class<E> classType, int value) {
        for (E enumConstant : classType.getEnumConstants()) {
            if (value == enumConstant.getValue()) {
                return enumConstant;
            }
        }
        return null;
    }

}
