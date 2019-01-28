package github.shiyajian.pretty.utils;

import github.shiyajian.pretty.commons.Enumerable;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
public class EnumUtil {

    public static <E extends Enumerable> E of(@NotNull Class<E> classType, int value) {
        for (E enumConstant : classType.getEnumConstants()) {
            if (value == enumConstant.getValue()) {
                return enumConstant;
            }
        }
        return null;
    }

    public static <E extends Enumerable> E of(@NotNull Class<E> classType, String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return of(classType, Integer.parseInt(value));
    }

}
