package github.shiyajian.pretty.dao;

import github.shiyajian.pretty.pojo.dto.PermissionDTO;
import org.springframework.stereotype.Repository;

/**
 * @author shiyajian
 * create: 2019-01-30
 */
@Repository
public interface PermissionDAO {

    /**
     * 根据用户id查询对应的权限
     * @param userId 用户id
     * @return 权限集合
     */
    PermissionDTO getPermissionsByUserId(String userId);
}
