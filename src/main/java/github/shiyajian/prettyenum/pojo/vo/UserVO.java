package github.shiyajian.prettyenum.pojo.vo;

import github.shiyajian.prettyenum.pojo.enums.GenderEnum;
import github.shiyajian.prettyenum.pojo.enums.UserStatusEnum;
import lombok.Data;

import java.util.Date;

/**
 * VO（ value object）,用户的值对象，可能会由多个DTO（DBO）对象组装而成。
 * @author shiyajian
 * create: 2019-01-27
 */
@Data
public class UserVO {

    /**
     * 用户名称
     */
    private String name;

    /**
     * 个人介绍
     */
    private String description;

    /**
     * 性别
     */
    private GenderEnum gender;

    /**
     * 用户当前状态
     */
    private UserStatusEnum status;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 年龄
     */
    private Integer age;
}
