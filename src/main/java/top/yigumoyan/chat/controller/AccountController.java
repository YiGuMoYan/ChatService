package top.yigumoyan.chat.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.yigumoyan.chat.entity.Account;
import top.yigumoyan.chat.result.Result;
import top.yigumoyan.chat.service.Impl.AccountServiceImpl;
import top.yigumoyan.chat.utils.DateUtils;
import top.yigumoyan.chat.utils.TokenUtils;

import javax.annotation.Resource;
import java.util.Date;

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
                return Result.ok(result.getToken());
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
            return Result.ok(true);
        }
        return Result.ok(false);
    }

    /**
     * 获取用户信息
     *
     * @param account 自身用户
     * @param id 目标id
     * @return 查询到的 account 对象
     */
    @PostMapping("/getAccountMessage/{id}")
    public Result getAccountMessage(@RequestBody Account account, @PathVariable String id) {
        if (TokenUtils.isToken(account)) {
            System.out.println("Token 正确");
            // 更新 Token 时间
            account.setGmtToken(new Date());
            accountService.updateById(account);
            // 查询目标信息
            account = new Account();
            account.setId(id);
            account = accountService.getById(account);
            // 查询到对应用户
            if (account != null) {
                return Result.ok(account);
            } else {
                return Result.ok(false);
            }
        }
        return Result.fail();
    }


    @PostMapping("/getAccountName/{id}")
    public Result getAccountName(@RequestBody Account account, @PathVariable String id) {
        if (TokenUtils.isToken(account)) {
            QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("name", "state").eq("id", id);
            Account result = accountService.getOne(queryWrapper);
            if (result != null) {
                return Result.ok(result);
            }
            return Result.ok(false);
        }
        return Result.fail();
    }
}
