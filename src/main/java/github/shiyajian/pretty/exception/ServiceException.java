package github.shiyajian.pretty.exception;

import github.shiyajian.pretty.commons.AbstractServiceException;
import github.shiyajian.pretty.commons.ResponseStatusEnum;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 普通的业务异常
 * ps:业务异常一般由于某些业务上的操作导致，假如我的列表30分钟没刷新，然后我删除一条信息
 * 结果这条信息已经被其他用户删除掉了，所以给前台返回此订单不存在，这个地方没必要记录异常日志
 * @author shiyajian
 * create: 2019-01-29
 */
public class ServiceException extends AbstractServiceException {

    public ServiceException() {
        super();
    }

    public ServiceException(@Nonnull String key, @Nullable Object... args) {
        super(key, args);
    }

    @Override
    public ResponseStatusEnum getResponseEnum() {
        return ResponseStatusEnum.UNKNOWN;
    }
}
