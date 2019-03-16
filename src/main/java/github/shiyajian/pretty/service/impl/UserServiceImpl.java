package github.shiyajian.pretty.service.impl;

import github.shiyajian.pretty.dao.PermissionDAO;
import github.shiyajian.pretty.dao.UserDAO;
import github.shiyajian.pretty.exception.NotFoundException;
import github.shiyajian.pretty.exception.ServiceException;
import github.shiyajian.pretty.mapper.UserMapper;
import github.shiyajian.pretty.pojo.dbo.UserDBO;
import github.shiyajian.pretty.pojo.dto.PermissionDTO;
import github.shiyajian.pretty.pojo.dto.UserDetailDTO;
import github.shiyajian.pretty.pojo.param.UserLoginParam;
import github.shiyajian.pretty.pojo.vo.UserDetailVO;
import github.shiyajian.pretty.pojo.vo.UserVO;
import github.shiyajian.pretty.service.UserService;
import github.shiyajian.pretty.utils.MD5Util;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * @author shiyajian
 * create: 2019-01-27
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    // 和数据库交互分类两类入口，一类是 mapper , 一类是 dao
    // mapper类是和数据库表一一对应的访问层
    // dao类是和业务挂钩，一般用来完成无法单表完成的操作，如关联查询

    // ------------------------ mapper ------------------//

    @Autowired
    private UserMapper userMapper;

    // ------------------------- dao --------------------//

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PermissionDAO permissionDAO;

    @Override
    public UserVO login(UserLoginParam param) {
        // mybatis-plus写法
        // UserDBO userDBO = Optional.of(userMapper.selectOne(new LambdaQueryWrapper<UserDBO>()
        //         .eq(UserDBO::getUsername, param.getUsername())
        //         .eq(UserDBO::getPassword, MD5Util.getMd5(param.getUsername(), param.getPassword()))))
        //         .orElseThrow(() -> new NotFoundException("exception.user.login.failed"));
        // UserVO vo = new UserVO();
        // BeanUtils.copyProperties(userDBO, vo);

        // mapper写法
        // userMapper.
        UserDBO record = new UserDBO();
        record.setUsername(param.getUsername());
        record.setPassword(MD5Util.getMd5(param.getUsername(), param.getPassword()));
        UserDBO userDBO = Optional.of(userMapper.selectOne(record))
                .orElseThrow(() -> new NotFoundException("exception.user.login.failed"));

        UserVO vo = new UserVO();
        BeanUtils.copyProperties(userDBO, vo);
        return vo;
    }

    @Override
    public UserDetailVO getUserById(String userId) {
        UserDetailDTO userDetail = Optional.of(userDAO.getUserDetailById(userId))
                .orElseThrow(() -> new NotFoundException("exception.user.not-found", userId));
        PermissionDTO permissions = permissionDAO.getPermissionsByUserId(userId);
        return new UserDetailVO.Builder()
                .userDetailDTO(userDetail)
                .permissionDTO(permissions)
                .build();
    }
}
