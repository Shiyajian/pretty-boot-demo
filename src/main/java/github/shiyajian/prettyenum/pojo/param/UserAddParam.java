package github.shiyajian.prettyenum.pojo.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import github.shiyajian.prettyenum.pojo.enums.GenderEnum;
import github.shiyajian.prettyenum.pojo.enums.UserStatusEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * 新增用户的请求参数，所有参数必须进行 Spring-Validation 注解标准
 * @author shiyajian
 * create: 2019-01-27
 */
@Data
public class UserAddParam {

    /**
     * 用户名称
     */
    @NotNull
    @Max(20)
    private String name;

    /**
     * 个人介绍
     */
    @Max(500)
    private String description;

    /**
     * 性别
     * notice: 如果前台传递的请求不符合枚举的范围，则转换之后为null
     *         我们只需要关注结果是否为null就行，传入的数字是否在限定的范围内无需关心
     */
    @NotNull
    private GenderEnum gender;

    /**
     * 用户当前状态
     */
    private UserStatusEnum status;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past
    @NotNull
    private Date birthday;

    /**
     * 年龄
     */
    @NotNull
    @Range(min = 0, max = 200)
    private Integer age;
}
