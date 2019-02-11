package github.shiyajian.pretty.mapper;

import github.shiyajian.pretty.pojo.dbo.UserDBO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
@Repository
public interface UserMapper extends Mapper<UserDBO> {
}
