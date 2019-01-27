package github.shiyajian.prettyenum.pojo.enums;

/**
 * 参照国家标准，GB/T 2261.1-2003
 * @author shiyajian
 * create: 2019-01-27
 */
public enum GenderEnum implements Enumerable {

    /** 男 */
    MALE(1, "male"),

    /** 女 */
    FEMALE(2, "female");

    private int value;

    private String key;

    GenderEnum(int value, String key) {
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


    /*
        因为我们配置好了mvc层和db层的枚举转换，
        整个项目所有不存在需要手动转换枚举类的地方，
        所以此类方法完全没有必要写
    */
    // @JsonCreator // 如果不配置全局枚举转换器，使用此注解
    // public static GenderEnum get(int value) {
    //     for (GenderEnum item : GenderEnum.values()) {
    //         if (value == item.getValue()) {
    //             return item;
    //         }
    //     }
    //     return null;
    // }
}
