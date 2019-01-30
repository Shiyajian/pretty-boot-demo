package github.shiyajian.pretty.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import github.shiyajian.pretty.pojo.dbo.UserDBO;
import org.springframework.stereotype.Repository;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
@Repository
public interface UserMapper extends BaseMapper<UserDBO> {
}
