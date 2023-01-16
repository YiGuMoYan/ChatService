package top.yigumoyan.chat.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yigumoyan.chat.entity.Account;
import top.yigumoyan.chat.service.Impl.AccountServiceImpl;

import javax.annotation.Resource;
import java.util.Date;

import static top.yigumoyan.chat.utils.AESUtils.Encrypt;

@Component
public class TokenUtils {

    private static AccountServiceImpl accountService;

    @Autowired
    public void setAccountService(AccountServiceImpl accountService) {
        TokenUtils.accountService = accountService;
    }

    /**
     * 验证 token 是否合法
     * @param account
     * @return
     */
    public static Boolean isToken(Account account) {
        // 验证 token
        QueryWrapper<Account> queryWrapperToken = new QueryWrapper<>();
        queryWrapperToken.select("id", "gmt_token")
                .eq("token", account.getToken());
        Account result = accountService.getOne(queryWrapperToken);
        // Token 验证成功
        return result != null && DateUtils.getDays(new Date(), result.getGmtToken()) < 3;
    }

    public static Account getAccountMessageByToken(String token) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name", "token", "gmt_token")
                .eq("token", token);
        Account account = accountService.getOne(queryWrapper);
        if (account != null && DateUtils.getDays(new Date(), account.getGmtToken()) < 3) {
            return account;
        }
        return null;
    }


    /**
     * 获取账号 Token
     *
     * @param account 账号
     * @return Token
     */
    public static String getAccountToken(Account account) {
        try {
            return Encrypt(account.getId() + new Date().toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置账号 Token
     *
     * @param account 账号
     */
    public static void setAccountToken(Account account) {
        account.setToken(getAccountToken(account));
        account.setGmtToken(new Date());
    }
}
