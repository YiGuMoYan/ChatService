package top.yigumoyan.chat.utils;

import top.yigumoyan.chat.entity.Account;

import java.util.Date;

import static top.yigumoyan.chat.utils.AESUtils.Encrypt;

public class TokenUtils {

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

    public static Account setAccountToken(Account account) {
        account.setToken(getAccountToken(account));
        account.setGmtToken(new Date());
        return account;
    }
}
