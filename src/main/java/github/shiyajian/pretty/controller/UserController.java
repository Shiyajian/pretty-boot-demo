package github.shiyajian.pretty.controller;

import github.shiyajian.pretty.commons.ResponseVO;
import github.shiyajian.pretty.exception.ServiceException;
import github.shiyajian.pretty.pojo.enums.UserStatusEnum;
import github.shiyajian.pretty.pojo.vo.UserVO;
import github.shiyajian.pretty.utils.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理控制器
 * @author shiyajian
 * create: 2019-01-27
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    ResponseVO<UserVO> getUsers() {
        UserVO vo = new UserVO();
        vo.setStatus(UserStatusEnum.LOCKED);
        return ResponseUtil.success(vo);
    }

    @GetMapping("/user/{userId}")
    ResponseVO<UserVO> getUserById(@PathVariable String userId) {
        throw new ServiceException("exception.user.not-found", "123456");
    }
}
