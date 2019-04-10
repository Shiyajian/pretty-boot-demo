package github.shiyajian.pretty.service;

import github.shiyajian.pretty.pojo.param.OrderPayParam;
import github.shiyajian.pretty.pojo.vo.OrderPaySuccessVO;

/**
 * 订单服务类
 * @author shiyajian
 * create: 2019-04-10
 */
public interface OrderService {


    /**
     * 支付订单
     * @param orderPayParam 订单支付的参数
     */
    OrderPaySuccessVO updateOrderState2Pay(OrderPayParam orderPayParam);

    /**
     * 订单退货
     */
    boolean updateOrderState2Refuse();
}
