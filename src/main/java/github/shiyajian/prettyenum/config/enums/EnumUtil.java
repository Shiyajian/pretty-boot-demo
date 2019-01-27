package github.shiyajian.prettyenum.config.enums;

import github.shiyajian.prettyenum.pojo.enums.Enumerable;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
public class EnumUtil {

    /**
     * 通过 value 找到对应的枚举类
     * @param classType 枚举类class
     * @param value     枚举类值
     * @param <E>       继承枚举接口的实例
     * @return 找到匹配返回，找不到匹配返回null
     */
    public static <E extends Enumerable> E of(@NotNull Class<E> classType, int value) {
        for (E enumConstant : classType.getEnumConstants()) {
            if (value == enumConstant.getValue()) {
                return enumConstant;
            }
        }
        return null;
    }

    /**
     * 通过 value 找到对应的枚举类
     * @param classType 枚举类class
     * @param value     枚举类值
     * @param <E>       继承枚举接口的实例
     * @return 找到匹配返回，找不到匹配返回null
     */
    public static <E extends Enumerable> E of(@NotNull Class<E> classType, String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return of(classType, Integer.parseInt(value));
    }
}
