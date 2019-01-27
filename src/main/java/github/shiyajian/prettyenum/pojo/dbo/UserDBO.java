package github.shiyajian.prettyenum.pojo.dbo;

import github.shiyajian.prettyenum.pojo.enums.GenderEnum;
import github.shiyajian.prettyenum.pojo.enums.UserStatusEnum;
import lombok.Data;

import java.util.Date;

/**
 * 用户的数据库映射对象，字段和数据库保持一致，所有参数必须以包装类表示
 * @author shiyajian
 * create: 2019-01-27
 */
@Data
public class UserDBO {

    private Long id;

    private String name;

    private GenderEnum gender;

    private UserStatusEnum status;

    private Integer age;

    private Date birthday;

    private String qq;

    private String wechat;

    private String description;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 创建人id
     */
    private String createdBy;

    /**
     * 修改时间
     */
    private Date updatedTime;

    /**
     * 修改人
     */
    private String updatedBy;

    /**
     * 是否删除
     */
    private Boolean deleted;
}
