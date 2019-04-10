package github.shiyajian.pretty.controller;

import github.shiyajian.pretty.commons.ResponseVO;
import github.shiyajian.pretty.pojo.param.OrderPayParam;
import github.shiyajian.pretty.pojo.vo.OrderPaySuccessVO;
import github.shiyajian.pretty.service.OrderService;
import github.shiyajian.pretty.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author shiyajian
 * create: 2019-04-10
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单支付
     * @param orderPayParam 支付订单时候需要的参数
     * @return 支付成功的订单信息
     */
    @PostMapping("/pay")
    public ResponseVO<OrderPaySuccessVO> updateOrderState2Pay(@Valid @RequestBody OrderPayParam orderPayParam) {
        return ResponseUtil.ok(
                orderService.updateOrderState2Pay(orderPayParam),
                "response.order.pay.success",
                orderPayParam.getOrderNo());
    }
}
