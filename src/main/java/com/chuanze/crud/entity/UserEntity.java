package com.chuanze.crud.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chuanze
 * @since 2020-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class UserEntity extends Model<UserEntity> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    /**
     * 姓名
     */
    @TableField(condition = SqlCondition.LIKE)
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 直属上级id
     */
    private Long managerId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 逻辑删除标识(0.未删除,1.已删除)
     */
    @TableLogic
    @TableField(select = false) // 查询时候不显示
    private Integer deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
