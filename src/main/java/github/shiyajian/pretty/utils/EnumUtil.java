package github.shiyajian.pretty.utils;

import github.shiyajian.pretty.commons.Enumerable;

import javax.annotation.Nonnull;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
public class EnumUtil {

    public static <E extends Enumerable> E of(@Nonnull Class<E> classType, int value) {
        for (E enumConstant : classType.getEnumConstants()) {
            if (value == enumConstant.getValue()) {
                return enumConstant;
            }
        }
        return null;
    }

}
