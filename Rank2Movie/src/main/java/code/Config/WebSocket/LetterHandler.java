package code.Config.WebSocket;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import code.DTO.letter.RoomDto;
import code.DTO.user.UserDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@RequiredArgsConstructor
@Getter @Setter
@EnableScheduling
public class LetterHandler extends TextWebSocketHandler
{

        // 세션 리스트
        private final List<WebSocketSession> sessionList = new LinkedList<>();
        // 유저명 기반으로 세션들을 저장함. (key : 유저명, value : 유저명이 같은 세션들)
        private final Map<String, List<WebSocketSession>> storageByUserName = new ConcurrentHashMap<>();

        // 채팅방을 리스트로 저장.
        private final List<RoomDto> roomList = new LinkedList<>();
        // 유저가 소유한 방 (key : 유저명, value : 유저가 속한 방)
        private final Map<String, RoomDto> roomMap = new ConcurrentHashMap<>();

        // 유저가 받은 새로운 편지 (key : 유저명, value : 유저가 받은 편지리스트)
        private final Map<String, List<RoomDto>> letterMap = new ConcurrentHashMap<>();

        //JSON parser
        private final JSONParser parser = new JSONParser();




        /////////////////////////////////////////
        // 연결 시작, 메시지 수신, 연결 종료 이벤트
        /////////////////////////////////////////
    
        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception
        {
            String userName = getNowUser(session).map(u -> u.getName()).orElse("anonymous");
            // 세션 리스트에 추가
            sessionList.add(session);

            // 만약, 이미 같은 유저명의 세션이 있다면, 그 리스트에 새 세션을 추가
            if(storageByUserName.containsKey(userName))
            {
                storageByUserName.get(userName).add(session);
            }
            else
            {
                // 그렇지 않다면 새로운 리스트를 만들어 거기에 새 새션을 추가.
                List<WebSocketSession> list = new LinkedList<>();
                list.add(session);
                
                storageByUserName.put(userName, list);
            }

            // 쌓은 편지 수를 저장하는 맵
            if(!letterMap.containsKey(userName))
            {
                letterMap.put(userName, new LinkedList<>());
            }

        }
    
        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage textMessage)
        {
            String message = textMessage.getPayload();
            String nowUser = getNowUser(session).map(u -> u.getName()).orElse("anonymous");

            try{
                JSONParser jsonParser = new JSONParser();
                JSONObject object = (JSONObject)jsonParser.parse(message);
                
                if(object.get("purpose").equals("createLetter"))
                {
                    // 수신된 메시지가 편지라면?
                    receivedLetter(nowUser, object);
                    
                }
                else if(object.get("purpose").equals("delete"))
                {
                    // 메시지 삭제
                    delete(nowUser, object);

                }



            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            
        }
    
        @Override
        // 클라이언트가 연결 해제될때 발생하는 이벤트
        public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
        {
            sessionList.remove(session); // 세션 리스트에서 먼저 제거
    
            String name = getNowUser(session).map(u -> u.getName()).orElse("anonymous"); // 현 유저의 이름을 받아온다. 
            List<WebSocketSession> list = storageByUserName.get(name); // 유저 이름으로 맵에서 리스트를 받아오고
            list.remove(session);   // 거기서 세션을 지워준다.
            if(list.isEmpty()) storageByUserName.remove(name); // 만약, 받아온 리스트가 비었다면 아예 그 이름(key)도 삭제해준다.
    
        }

        //////////////////////
        // scheduled
        //////////////////////
        // 현재 각 유저가 가진 편지의 수/편지 내용/자기가 만든 방을 보내준다.
        @Scheduled(fixedDelay=1000)
        public void sendRoom()
        {
            int userCnt = nowCnt();

            // 각 유저에게 정보를 보내준다.
            // 현재 접속된 유저의 수
            // 유저가 속한 방들, 유저가 받은 편지들, 편지의 수
            for(String s : storageByUserName.keySet())
            {
                RoomDto room = roomMap.get(s);
                List<RoomDto> letters = letterMap.get(s);
                List<WebSocketSession> connections = storageByUserName.get(s);

                // 여기서부터 편지를 JSONArray에 담아줄거임.
                JSONArray letterArray = new JSONArray();
                for(RoomDto l : letters)
                {
                    letterArray.put(l.toJSON());
                }

                // JSON에 정보들 담아주기
                JSONObject object = new JSONObject();
                object.put("purpose", "status");
                object.put("userCnt", userCnt);
                object.put("letterCnt", letterArray.length());

                object.put("letters", letterArray);
               if(room != null) object.put("room", room.toJSON());
               else object.put("room", null);

                // 해당 유저의 연결들에 JSON을 송신
                connections.forEach(c ->{
                    try{
                        c.sendMessage(new TextMessage(object.toJSONString()));
                    }catch(Exception e){}
                    
                });
                
            }
            

        }
    
        //////////////////////
        // 메시지 수신 이벤트들
        //////////////////////

        // 받은 메시지가 편지일 경우.
        private void receivedLetter(String sender, JSONObject object)
        {   
            Integer howMany =  Integer.parseInt(String.valueOf(object.get("howMany")));
            
            // JSON에서 값 받아오기
            String title = String.valueOf(object.get("title"));
            String content = String.valueOf(object.get("content"));
            String movie = String.valueOf(object.get("movie"));
            
            // 방 Dto를 만들자
            RoomDto room = new RoomDto();
            room.setMaster(sender);
            room.setTitle(title);
            room.setContent(content);
            room.setMovie(movie);
            
            // 유저명을 리스트로 받아온다.
            List<String> userNames = Arrays.asList(storageByUserName.keySet().stream().filter(s -> !s.equals(sender)).toArray(String[]::new));
            if(userNames.size() < howMany) howMany = userNames.size(); // 보낼 사람의 수보다 현 접속자 수가 더 적다면, 현 접속자 수로 바꿔준다.
            
            Collections.shuffle(userNames); // 받아온 리스트를 랜덤 정렬해준다.
            
            // 선정된 사람들의 이름이 담긴 배열.
            List<String> selectedUsers = userNames.subList(0, howMany); // 보낼 사람만큼 끊어준다.

            // 방 리스트에 넣어준다.
            roomList.add(room);

            // 방 판 사람의 방 목록에 넣어준다.
            roomMap.put(sender, room);

            // 선정된 사람 각각의 편지함에 넣어준다.
            selectedUsers.forEach(s -> letterMap.get(s).add(room));

        }

        // 편지/방 삭제하는 기능
        private void delete(String name, JSONObject object)
        {
            
            System.out.println(object);
            if(object.get("mine").equals(true))
            {
                
                roomMap.remove(name);
            }
            else
            {
                
                try 
                {
                    List<RoomDto> list = letterMap.get(name);
                    RoomDto letter = list.stream().filter(r -> r.getRoomId().equals((String)object.get("target")))
                                                  .findFirst().orElseThrow(() -> new Exception("없는 편지"));
    
                    list.remove(letter);
                } catch (Exception e) {
                    
                    e.printStackTrace();
                }
            }



        }       

        //////////////////////
        // 다양한 기능들
        //////////////////////

        // 유저를 반환
        private Optional<UserDto> getNowUser(WebSocketSession session)
        {

            Object object = (UsernamePasswordAuthenticationToken)session.getPrincipal();

            if(object.equals("anonymousUser")) return Optional.ofNullable(null);
            else 
            {
                UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) object;
                return Optional.of((UserDto)token.getPrincipal());
            }
            

        }

        // 현재 접속 중인 인원 수를 JSON으로 반환
        private int nowCnt()
        {
            int cnt = storageByUserName.size();                  // 로그인 유저
            if(storageByUserName.containsKey("anonymous"))
            {
                cnt--; // 이미 위에서 anonymous라는 유저를 더해줬기 때문.(anonymous는 익명 유저의 통칭이다.)
                cnt += storageByUserName.get("anonymous").size();  // 비 로그인 유저
            }

            return cnt;
        }
    
    

}
