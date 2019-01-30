package github.shiyajian.pretty.pojo.param;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author shiyajian
 * create: 2019-01-30
 */
@Data
public class UserLoginParam {

    /**
     * 用户名
     */
    @Max(value = 20, message = "密码不能超过20位")
    @NotNull(message = "用户名不能为空！")
    private String username;

    /**
     * 密码
     */
    @Max(value = 20, message = "密码不能超过20位")
    @NotNull(message = "密码不能为空")
    private String password;
}
