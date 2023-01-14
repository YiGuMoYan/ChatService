package top.yigumoyan.chat.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yigumoyan.chat.entity.Account;
import top.yigumoyan.chat.result.Result;
import top.yigumoyan.chat.service.Impl.AccountServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 忆古陌烟
 * @since 2023-01-14
 */
@Controller
@ResponseBody
@RequestMapping("/chat/account")
public class AccountController {

    @Resource
    private AccountServiceImpl accountService;

    @PostMapping("/login")
    public Result login(HttpSession httpSession , @RequestBody Account account) {
        System.out.println(account);
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", account.getId())
                .eq("password", account.getPassword());
        Account one = accountService.getOne(queryWrapper);
        if (one != null) {
            httpSession.setAttribute("id", account.getId());
            return Result.ok(one);
        }
        return Result.ok(false);
    }

    @PostMapping("/register")
    public Result register(String password) {
        Account account = new Account();
        account.setPassword(password);
        boolean save = accountService.save(account);
        return Result.ok(save);
    }
}
