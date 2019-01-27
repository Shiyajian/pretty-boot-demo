package github.shiyajian.prettyenum.pojo.enums;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
public interface Enumerable<E extends Enumerable> {

    /**
     * 获取在i18n文件中对应的 key
     */
    String getKey();

    /**
     * 获取最终保存到数据的值
     */
    int getValue();

}
