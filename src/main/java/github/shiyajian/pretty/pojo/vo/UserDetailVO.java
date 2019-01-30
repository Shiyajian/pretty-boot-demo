package github.shiyajian.pretty.pojo.vo;

import github.shiyajian.pretty.pojo.dto.PermissionDTO;
import github.shiyajian.pretty.pojo.dto.UserDetailDTO;

import javax.annotation.Nullable;

/**
 * @author shiyajian
 * create: 2019-01-30
 */
public class UserDetailVO {

    public static class Builder {

        private UserDetailVO vo;

        public Builder() {
            this.vo = new UserDetailVO();
        }

        public UserDetailVO build() {
            return this.vo;
        }

        public Builder userDetailDTO(@Nullable UserDetailDTO dto) {
            if (null != dto) {
                // this.vo.set
            }
            return this;
        }

        public Builder permissionDTO(@Nullable PermissionDTO dto) {
            if (null != dto) {
                // this.vo.set
            }
            return this;
        }
    }
}
