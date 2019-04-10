package github.shiyajian.pretty.pojo.vo;

import lombok.Data;

/**
 * @author shiyajian
 * create: 2019-04-10
 */
@Data
public class OrderPaySuccessVO {

    /**
     * 支付结果
     */
    private Boolean success;

    /**
     * 支付后生成的订单号
     */
    private String orderNo;
}
