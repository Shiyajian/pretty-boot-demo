package github.shiyajian.pretty.utils;

import github.shiyajian.pretty.commons.PageDTO;
import github.shiyajian.pretty.commons.ResponseStatusEnum;
import github.shiyajian.pretty.commons.ResponseVO;

import java.util.List;

/**
 * @author shiyajian
 * create: 2018-10-24
 */
public final class ResponseUtil {

    private ResponseUtil() { /* no instance */ }

    /**
     * 本次操作成功，无返回实体，无响应信息
     * @return ResponseVO
     */
    public static ResponseVO ok() {
        return new ResponseVO();
    }

    /**
     * 本次操作成功，返回实体，无响应信息
     * @param data 数据实体
     * @param <T>  实体对应的类型
     * @return ResponseVO<T>
     */
    public static <T> ResponseVO<T> ok(T data) {
        return new ResponseVO<>(data);
    }

    /**
     * 本次操作成功，返回分页实体，无响应信息
     * @param pageData 数据集合
     * @param <T>      实体对应的类型
     * @return ResponseVO<List < T>>
     */
    public static <T> ResponseVO<List<T>> ok(PageDTO<T> pageData) {
        return new ResponseVO<>(pageData.getTotal(), pageData.getData());
    }

    /**
     * 本次操作成功，返回实体，返回响应信息
     * @param data    数据实体
     * @param i18nKey 响应信息在国际化文件中对应的key值
     * @param args    响应信息中占位符对应的值
     * @param <T>     实体对应的类型
     * @return ResponseVO<T>
     */
    public static <T> ResponseVO<T> ok(T data, String i18nKey, Object... args) {
        return new ResponseVO<>(data, I18nMessageUtil.getMessage(i18nKey, args));
    }

    /**
     * 本次操作成功，返回分页实体，返回响应信息
     * @param pageData 数据集合
     * @param i18nKey  响应信息在国际化文件中对应的key值
     * @param args     响应信息中占位符对应的值
     * @param <T>      实体对应的类型
     * @return ResponseVO<List < T>>
     */
    public static <T> ResponseVO<List<T>> ok(PageDTO<T> pageData, String i18nKey, Object... args) {
        return new ResponseVO<>(pageData.getTotal(), pageData.getData(), I18nMessageUtil.getMessage(i18nKey, args));
    }

    /**
     * 本次操作成功，无返回实体，返回响应信息
     * @param i18nKey 响应信息在国际化文件中对应的key值
     * @param args    响应信息中占位符对应的值
     * @return ResponseVO<T>
     */
    public static ResponseVO echo(String i18nKey, Object... args) {
        return new ResponseVO<>(null, null, I18nMessageUtil.getMessage(i18nKey, args));
    }

    /**
     * 本次操作失败，采用默认的提示信息
     * @param statusEnum 异常枚举类
     * @return ResponseVO<T>
     */
    public static <T> ResponseVO<T> fail(ResponseStatusEnum statusEnum) {
        return new ResponseVO<>(statusEnum.getValue(), statusEnum.getText());
    }

    /**
     * 本次操作失败，采用自定义的提示信息
     * @param statusEnum 异常枚举类
     * @param i18nKey    响应信息在国际化文件中对应的key值
     * @param args       响应信息中占位符对应的值
     * @return ResponseVO<T>
     */
    public static <T> ResponseVO<T> fail(ResponseStatusEnum statusEnum, String i18nKey, Object... args) {
        return new ResponseVO<>(statusEnum.getValue(), I18nMessageUtil.getMessage(i18nKey, args));

    }
}
