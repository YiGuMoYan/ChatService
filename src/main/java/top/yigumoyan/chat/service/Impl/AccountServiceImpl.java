package top.yigumoyan.chat.service.Impl;

import top.yigumoyan.chat.entity.Account;
import top.yigumoyan.chat.mapper.AccountMapper;
import top.yigumoyan.chat.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 忆古陌烟
 * @since 2023-01-13
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
