package github.shiyajian.pretty.strategy.pay;

import github.shiyajian.pretty.config.properties.AliPayProperties;
import github.shiyajian.pretty.pojo.dto.OrderPayDTO;
import github.shiyajian.pretty.pojo.enums.PayEnum;
import github.shiyajian.pretty.pojo.vo.OrderPaySuccessVO;
import github.shiyajian.pretty.strategy.PayStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author shiyajian
 * create: 2019-04-10
 */
@Component
public class AliPayStrategy implements PayStrategy {

    @Autowired
    private AliPayProperties aliPayProperties;

    @Override
    public PayEnum def() {
        return PayEnum.ALIPAY;
    }

    @Override
    public OrderPaySuccessVO pay(OrderPayDTO orderPayDTO) {
        return null;
    }

    @Override
    public boolean refuse() {
        return false;
    }

    @Override
    public boolean cash() {
        return false;
    }
}
