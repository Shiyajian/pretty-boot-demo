package github.shiyajian.pretty.pojo.param;

import github.shiyajian.pretty.pojo.enums.PayEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author shiyajian
 * create: 2019-04-10
 */
@Data
@Valid
public class OrderPayParam {

    /**
     * 订单号
     */
    @NotNull
    @Range(min = 0, max = 32)
    private String orderNo;

    /**
     * 支付类型
     */
    @NotNull
    private PayEnum payEnum;

    /**
     * 防重校验token
     */
    @NotNull
    @Range(min = 0, max = 32)
    private String payToken;
}
