package top.yigumoyan.chat.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yigumoyan.chat.entity.AccountOnline;
import top.yigumoyan.chat.result.Result;
import top.yigumoyan.chat.service.Impl.AccountOnlineServiceImpl;

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
@ResponseBody
@RequestMapping("/chat/accountOnline")
public class AccountOnlineController {

    @Resource
    private AccountOnlineServiceImpl accountOnlineService;

    @PostMapping("/isOnline")
    public Result isOnline(String id) {
        QueryWrapper<AccountOnline> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_id", id);
        return Result.ok(accountOnlineService.getOne(queryWrapper) != null);
    }
}
