package github.shiyajian.pretty.commons;

import lombok.Data;

import java.util.List;

/**
 * 全局统一分页实体
 *
 * @author shiyajian
 * create: 2018-10-24
 */
@Data
public class PageDTO<T> {

    /**
     * 数据总条数
     */
    private Long total;

    /**
     * 数据内容
     */
    private List<T> data;

    public PageDTO() {
    }

    public PageDTO(Long total, List<T> data) {
        this.total = total;
        this.data = data;
    }
}
