package github.shiyajian.pretty.exception;

import github.shiyajian.pretty.commons.AbstractServiceException;
import github.shiyajian.pretty.commons.ResponseEnum;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 资源没有找到
 * @author shiyajian
 * create: 2019-01-29
 */
public class NotFoundException extends AbstractServiceException {

    public NotFoundException(@Nonnull String key, @Nullable Object... args) {
        super(key, args);
    }

    @Override
    public ResponseEnum getResponseEnum() {
        return ResponseEnum.NOT_FOUND;
    }
}
