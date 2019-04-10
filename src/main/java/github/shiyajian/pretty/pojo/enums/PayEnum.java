package github.shiyajian.pretty.pojo.enums;

import github.shiyajian.pretty.commons.Enumerable;

/**
 * 支付方式枚举类
 */
public enum PayEnum implements Enumerable {

    /** 支付宝 */
    ALIPAY(1, "enum.properties.ali"),

    /** 微信 */
    WECHAT(2, "enum.properties.wechat");

    private int value;

    private String key;

    PayEnum(int value, String key) {
        this.value = value;
        this.key = key;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
