package github.shiyajian.pretty.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.shiyajian.pretty.pojo.enums.GenderEnum;
import github.shiyajian.pretty.pojo.enums.UserStatusEnum;
import github.shiyajian.pretty.utils.DateUtil;
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

    /**
     * 创建时间
     */
    @JsonProperty("created_time")
    @JsonFormat(pattern = DateUtil.DATETIME_PATTERN)
    private Date createdTime;
}
