package top.yigumoyan.chat.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import top.yigumoyan.chat.entity.Account;
import top.yigumoyan.chat.result.Result;
import top.yigumoyan.chat.service.Impl.AccountServiceImpl;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 忆古陌烟
 * @since 2023-01-13
 */
@Controller
@RequestMapping("/chat/account")
public class AccountController {

    @Resource
    private AccountServiceImpl accountService;

    @PostMapping("register")
    public Result register (String password) {
        Account account = new Account();
        account.setPassword(password);
        boolean save = accountService.save(account);
        return Result.ok(save);
    }

}
