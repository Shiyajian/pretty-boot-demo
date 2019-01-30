package github.shiyajian.pretty.pojo.dbo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import github.shiyajian.pretty.pojo.enums.GenderEnum;
import github.shiyajian.pretty.pojo.enums.UserStatusEnum;
import lombok.Data;

import java.util.Date;

/**
 * 用户的数据库映射对象，字段和数据库保持一致，所有参数必须以包装类表示
 * @author shiyajian
 * create: 2019-01-27
 */
@Data
@TableName("t_user")
public class UserDBO {

    /**
     * 用户id
     */
    @TableId
    private Long id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private GenderEnum gender;

    /**
     * 状态
     */
    private UserStatusEnum status;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * qq
     */
    private String qq;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 个人介绍
     */
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
