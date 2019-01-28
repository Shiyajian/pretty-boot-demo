package github.shiyajian.pretty.pojo.enums;

import github.shiyajian.pretty.commons.Enumerable;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
public enum UserStatusEnum implements Enumerable {

    /** 正常 */
    NORMAL(0, "enum.user.status.normal"),

    /** 锁定 */
    LOCKED(-1, "enum.user.status.locked");

    private int value;

    private String key;

    UserStatusEnum(int value, String key) {
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
