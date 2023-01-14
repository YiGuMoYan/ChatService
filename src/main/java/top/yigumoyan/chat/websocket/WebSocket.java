package top.yigumoyan.chat.websocket;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yigumoyan.chat.entity.AccountOnline;
import top.yigumoyan.chat.service.Impl.AccountOnlineServiceImpl;
import top.yigumoyan.chat.service.Impl.AccountServiceImpl;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@ServerEndpoint("/login/{accountId}")
public class WebSocket {

    private static AccountServiceImpl accountService;
    private static AccountOnlineServiceImpl accountOnlineService;

    @Autowired
    public void setPersonService(AccountServiceImpl accountService, AccountOnlineServiceImpl accountOnlineService) {
        WebSocket.accountService = accountService;
        WebSocket.accountOnlineService = accountOnlineService;
    }

    private String accountId;

    private static final ConcurrentHashMap<String, Session> sessionPool = new ConcurrentHashMap<String, Session>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "accountId") String accountId) {
        try {
            // 判断账户是否在 Session 池中
            // 如果再 Session 池中，则为重复登录
            if (sessionPool.get(accountId) == null) {
                this.accountId = accountId;
                sessionPool.put(accountId, session);
                // 添加到在线数据库
                addAccountOnline();
                System.out.println("连接成功");
            } else {
                session.close();
                System.out.println("连接失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误,原因:" + error.getMessage());
        error.printStackTrace();
    }

    @OnClose
    public void onClose() {
        deleteAccountOnline();
    }

    public void addAccountOnline() {
        AccountOnline accountOnline = new AccountOnline();
        accountOnline.setAccountId(Long.valueOf(this.accountId));
        accountOnlineService.save(accountOnline);
    }

    public void deleteAccountOnline() {
        QueryWrapper<AccountOnline> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account_id", this.accountId);
        accountOnlineService.remove(queryWrapper);
    }
}
