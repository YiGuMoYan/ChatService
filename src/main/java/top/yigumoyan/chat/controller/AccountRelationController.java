package top.yigumoyan.chat.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.yigumoyan.chat.entity.Account;
import top.yigumoyan.chat.entity.AccountRelation;
import top.yigumoyan.chat.result.Result;
import top.yigumoyan.chat.service.Impl.AccountRelationServiceImpl;
import top.yigumoyan.chat.service.Impl.AccountServiceImpl;
import top.yigumoyan.chat.utils.TokenUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 忆古陌烟
 * @since 2023-01-13
 */
@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("/chat/accountRelation")
public class AccountRelationController {

    @Resource
    private AccountRelationServiceImpl accountRelationService;

    @Resource
    private AccountServiceImpl accountService;

    @PostMapping("/getAccountRelation")
    public Result getAccountRelation(@RequestBody Account account) {
        if ((account = TokenUtils.getAccountMessageByToken(account.getToken())) != null) {
            QueryWrapper<AccountRelation> queryWrapperAccountRelation = new QueryWrapper<>();
            queryWrapperAccountRelation.eq("account_id1", account.getId())
                    .or()
                    .eq("account_id2", account.getId());
            List<AccountRelation> list = accountRelationService.list(queryWrapperAccountRelation);
            List<String> stringList = new ArrayList<>();
            for (AccountRelation accountRelation : list) {
                stringList.add(account.getId().equals(accountRelation.getAccountId1()) ? accountRelation.getAccountId2() : accountRelation.getAccountId1());
            }
            return Result.ok(stringList);
        }
        return Result.fail();
    }
}
