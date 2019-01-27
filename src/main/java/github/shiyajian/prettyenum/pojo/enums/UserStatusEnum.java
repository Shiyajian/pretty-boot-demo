package github.shiyajian.prettyenum.pojo.enums;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
public enum UserStatusEnum implements Enumerable {

    /** 正常 */
    NORMAL(0, "male"),

    /** 锁定 */
    LOCKED(-1, "female");

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
