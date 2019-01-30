package github.shiyajian.pretty.dao;

import github.shiyajian.pretty.pojo.dto.UserDetailDTO;
import org.springframework.stereotype.Repository;

/**
 * 如果是单表查询，则使用单表的Mapper操作，如果结果是从两三张表中获得的，那么就找对应业务对应的 dao
 * dao 层统一返回DTO
 * @author shiyajian
 * create: 2019-01-30
 */
@Repository
public interface UserDAO {

    /**
     * 根据id，查询用户的详细信息
     * @param userId 用户id
     * @return 用户的详细信息
     */
    UserDetailDTO getUserDetailById(String userId);
}
