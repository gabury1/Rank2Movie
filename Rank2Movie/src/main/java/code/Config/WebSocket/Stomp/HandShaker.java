package code.Config.WebSocket.Stomp;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import code.Controller.MessageController;
import code.Service.UserService;

public class HandShaker extends HttpSessionHandshakeInterceptor 
{
    @Autowired
    MessageController messageController;
    @Autowired
    UserService userService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, 
                                    Map<String, Object> attributes) throws Exception 
    {
        
        if (request instanceof ServletServerHttpRequest) 
        {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);

            if (session != null) {
                attributes.put("HTTPSESSIONID", session.getId());
                attributes.put("name", userService.nowUser().map(u -> u.getName()).orElse("anonymous"));
            }
        }
        return true;
    }

    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
    }
}
