package top.yigumoyan.chat.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 忆古陌烟
 * @since 2023-01-13
 */
@Getter
@Setter
@TableName("account_group_relation")
public class AccountGroupRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
      private Integer id;

    /**
     * 账户
     */
    private Integer accountId;

    /**
     * 群聊
     */
    private Integer groupId;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;


}
