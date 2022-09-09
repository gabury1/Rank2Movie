package code.Config.WebSocket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component @Getter @Setter
@RequiredArgsConstructor
public class ChattingHandler extends TextWebSocketHandler
{
    // 세션 아이디 기반으로 세션들을 저장함.
    private final List<WebSocketSession> sessionList = new LinkedList<>();
    // 유저명 기반으로 세션들을 저장함.
    private final Map<String, List<WebSocketSession>> storageByUserName = new ConcurrentHashMap<>();

    @Override
    // 처음으로 세션에 소켓을 연결했을때 발생하는 이벤트
    public void afterConnectionEstablished(WebSocketSession session) throws Exception
    {
        String userName = getNowUser(session);
        sessionList.add(session);
        if(storageByUserName.containsKey(userName))
        {
            storageByUserName.get(userName).add(session);
        }
        else
        {
            List<WebSocketSession> list = new LinkedList<>();
            list.add(session);

            storageByUserName.put(userName, list);
        }

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage)
    {
        sessionList.forEach(
        (s)-> {
            try{
                String content = getNowUser(session) + " : " + textMessage.getPayload();
                s.sendMessage(new TextMessage(content));
            }
            catch(Exception e){}
            
        }
        );

    }

    @Override
    // 클라이언트가 연결 해제될때 발생하는 이벤트
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
    {
        sessionList.remove(session); // 세션 리스트에서 먼저 제거

        String name = getNowUser(session); // 현 유저의 이름을 받아온다. 
        List<WebSocketSession> list = storageByUserName.get(name); // 유저 이름으로 리스트를 받아오고
        list.remove(session);   // 거기서 세션을 지워준다.
        if(list.isEmpty()) storageByUserName.remove(name); // 만약, 받아온 리스트가 비었다면 아예 그 항목도 삭제해준다.

    }


    // 다양한 기능들
    private String getNowUser(WebSocketSession session)
    {
        try{
            return session.getPrincipal().getName();
        } catch(NullPointerException e)
        {

            return "anonymous";
        }
    }


}
