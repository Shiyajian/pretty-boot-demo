package github.shiyajian.pretty.strategy;

import github.shiyajian.pretty.holder.SpringContextHolder;
import github.shiyajian.pretty.pojo.dto.OrderPayDTO;
import github.shiyajian.pretty.pojo.enums.PayEnum;
import github.shiyajian.pretty.pojo.vo.OrderPaySuccessVO;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 统一支付策略类
 * @author shiyajian
 * create: 2019-04-10
 */
public interface PayStrategy {

    /**
     * 保存所有的支付策略
     */
    ConcurrentHashMap<PayEnum, Class<? extends PayStrategy>> HOLDER = new ConcurrentHashMap<>();

    /**
     * 支付策略子类在初始化时候，将自己添加到集合中，方便后面使用
     */
    @PostConstruct
    default void register() {
        HOLDER.put(def(), this.getClass());
    }

    /**
     * 定义此策略所对应的枚举类
     * @return 策略对应的枚举类
     */
    PayEnum def();

    /**
     * 根据支付枚举值找到对应的策略
     * @param payEnum 支付渠道对应的枚举类
     * @return 枚举对应策略在Spring中的单例实现
     */
    static PayStrategy route(PayEnum payEnum) {

        checkArgument(null != payEnum, "枚举类不能为空");

        Class<? extends PayStrategy> targetClazz = PayStrategy.HOLDER.get(payEnum);

        checkArgument(null != targetClazz, "不支持的支付策略");

        return SpringContextHolder.getBean(targetClazz);

    }

    /**
     * 支付
     * @param orderPayDTO 支付所需要的参数
     */
    OrderPaySuccessVO pay(OrderPayDTO orderPayDTO);

    /**
     * 退款
     * @return 结果
     */
    boolean refuse();

    /**
     * 提现
     * @return 结果
     */
    boolean cash();
}
