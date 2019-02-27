package github.shiyajian.pretty.controller;

import github.shiyajian.pretty.commons.ResponseVO;
import github.shiyajian.pretty.pojo.enums.UserStatusEnum;
import github.shiyajian.pretty.pojo.param.UserLoginParam;
import github.shiyajian.pretty.pojo.vo.UserDetailVO;
import github.shiyajian.pretty.pojo.vo.UserVO;
import github.shiyajian.pretty.service.UserService;
import github.shiyajian.pretty.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户管理控制器
 * @author shiyajian
 * create: 2019-01-27
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    ResponseVO<List<UserVO>> getUsers() {
        List<UserVO> vos = new ArrayList<>();
        UserVO vo = new UserVO();
        vo.setStatus(UserStatusEnum.LOCKED);
        vos.add(vo);
        return ResponseUtil.ok(vos);
    }

    @GetMapping("/user/{userId}")
    ResponseVO<UserDetailVO> getUserById(@PathVariable String userId) {
        return ResponseUtil.ok(userService.getUserById(userId));
    }

    /**
     * 登录
     * @param param 账号和密码
     * @return 成果返回用户信息，失败由异常拦截统一处理
     */
    @PostMapping("/login")
    ResponseVO<UserVO> login(@RequestBody @Valid UserLoginParam param) {
        return ResponseUtil.ok(userService.login(param));
    }

}
