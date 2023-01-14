package top.yigumoyan.chat.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.yigumoyan.chat.entity.Account;
import top.yigumoyan.chat.result.Result;
import top.yigumoyan.chat.service.Impl.AccountServiceImpl;
import top.yigumoyan.chat.utils.TokenUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 忆古陌烟
 * @since 2023-01-14
 */
@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("/chat/account")
public class AccountController {

    @Resource
    private AccountServiceImpl accountService;

    /**
     * 登录
     *
     * @param account 前端传 account 对象
     * @return account 对象
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        // 验证账号密码
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", account.getId()).eq("password", account.getPassword());
        Account result = accountService.getOne(queryWrapper);
        // 判断账号密码是否正确
        if (result != null) {
            // 在每次登录时，更新 Token
            TokenUtils.setAccountToken(result);
            // 更新成功，允许登录
            if (accountService.updateById(result)) {
                return Result.ok(result);
            }
        }
        return Result.ok(false);
    }

    /**
     * 注册
     *
     * @param account 前端传 account 对象
     * @return account 对象
     */
    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        // 给新账号增加 Token
        TokenUtils.setAccountToken(account);
        if (accountService.save(account)) {
            return Result.ok(account);
        }
        return Result.ok(false);
    }
}
