package github.shiyajian.pretty.service;

import github.shiyajian.pretty.pojo.param.UserLoginParam;
import github.shiyajian.pretty.pojo.vo.UserDetailVO;
import github.shiyajian.pretty.pojo.vo.UserVO;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
public interface UserService {

    /**
     * 登录
     * @param param 账号和密码
     * @return  成功返回账号信息，失败抛出业务异常
     */
    UserVO login(UserLoginParam param);

    UserDetailVO getUserById(String userId);
}
