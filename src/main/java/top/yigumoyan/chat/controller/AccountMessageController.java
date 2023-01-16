package top.yigumoyan.chat.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import top.yigumoyan.chat.entity.Account;
import top.yigumoyan.chat.entity.AccountMessage;
import top.yigumoyan.chat.result.Result;
import top.yigumoyan.chat.service.Impl.AccountMessageServiceImpl;
import top.yigumoyan.chat.utils.TokenUtils;

import javax.annotation.Resource;
import java.util.List;

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
@CrossOrigin
@RequestMapping("/chat/accountMessage")
public class AccountMessageController {

    @Resource
    private AccountMessageServiceImpl accountMessageService;

    @PostMapping("/getAccountMessage")
    public Result getAccountMessage(@RequestBody JSONObject jsonObject) {
        Account account;
        if ((account = TokenUtils.getAccountMessageByToken(jsonObject.getString("token"))) != null) {
            QueryWrapper<AccountMessage> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("from_id", account.getId(), jsonObject.getString("id"))
                    .or()
                    .in("to_id", account.getId(), jsonObject.getString("id"));
            List<AccountMessage> accountMessageList = accountMessageService.list(queryWrapper);
            return Result.ok(accountMessageList);
        }
        return Result.fail();
    }
}
