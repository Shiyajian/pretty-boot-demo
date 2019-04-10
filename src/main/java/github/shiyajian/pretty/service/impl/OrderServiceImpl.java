package github.shiyajian.pretty.service.impl;

import github.shiyajian.pretty.pojo.dto.OrderPayDTO;
import github.shiyajian.pretty.pojo.param.OrderPayParam;
import github.shiyajian.pretty.pojo.vo.OrderPaySuccessVO;
import github.shiyajian.pretty.service.OrderService;
import github.shiyajian.pretty.strategy.PayStrategy;
import org.springframework.stereotype.Service;

/**
 * @author shiyajian
 * create: 2019-04-10
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderPaySuccessVO updateOrderState2Pay(OrderPayParam orderPayParam) {

        // 1、根据 orderPayParam 中的订单号，查询出对应的商品数量和价格等，生成新的实体,此处假设为OrderPayDTO
        OrderPayDTO orderPayDTO = new OrderPayDTO();

        // 2、根据路由跳转到指定的策略实现类中调用相关的方法
        OrderPaySuccessVO result = PayStrategy.route(orderPayParam.getPayEnum()).pay(orderPayDTO);

        // 将result中支付结果组装成需要的形式
        OrderPaySuccessVO orderPaySuccessVO = new OrderPaySuccessVO();
        orderPaySuccessVO.setSuccess(true);
        return orderPaySuccessVO;
    }

    @Override
    public boolean updateOrderState2Refuse() {
        return false;
    }
}
