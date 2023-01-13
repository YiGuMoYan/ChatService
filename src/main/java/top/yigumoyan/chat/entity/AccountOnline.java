package top.yigumoyan.chat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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
@NoArgsConstructor
@AllArgsConstructor
@TableName("account_online")
public class AccountOnline implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户id
     */
    private Long accountId;


}
