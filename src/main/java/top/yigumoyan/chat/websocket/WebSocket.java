package top.yigumoyan.chat.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.helpers.AttributesImpl;
import top.yigumoyan.chat.entity.Account;
import top.yigumoyan.chat.mapper.AccountMapper;
import top.yigumoyan.chat.service.Impl.AccountServiceImpl;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Slf4j
@ServerEndpoint("/websocket/{id}")
public class WebSocket {

    @Autowired
    private AccountServiceImpl accountService;

    private Session session;
    private String id;

    private static final CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();
    private static final ConcurrentHashMap<String, Session> sessionPool = new ConcurrentHashMap<String, Session>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "id") Long accountId, @PathParam(value = "id") String accountPassword) {
        // 测试环境，暂时不检索是否在 账号数据库中
        // 这里用于防止其他连接
        //if (accountService.selectAccountById(userId) != null) {
        try {
            Account account = new Account();
            account.setId(accountId);
            account.setPassword(accountPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //}
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误,原因:" + error.getMessage());
        error.printStackTrace();
    }

    @OnClose
    public void onClose() {
        if (webSockets.contains(this)) {
            webSockets.remove(this);
        }
        if (id != null && sessionPool.containsKey(id)) {
            sessionPool.remove(id);
        }
        log.info("有用户退出，当前在线列表:" + sessionPool);
    }
}
