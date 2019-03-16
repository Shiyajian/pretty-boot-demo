package github.shiyajian.pretty.pojo.param;

import github.shiyajian.pretty.pojo.enums.UserStatusEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * @author shiyajian
 * create: 2019-02-27
 */
@Data
public class UserCreateParam {

    private String name;

    @Range(min = 0, max = 150)
    private Integer age;

    private UserStatusEnum status;
}
