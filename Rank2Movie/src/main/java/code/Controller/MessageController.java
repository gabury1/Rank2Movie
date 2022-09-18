package code.Controller;

import java.security.Principal;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

import code.DTO.letter.MessageDto;
import code.DTO.user.UserDto;
import code.Service.UserService;

@Controller
public class MessageController 
{
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/room/{roomId}")
    public String message(@Payload MessageDto message, @DestinationVariable("roomId") String roomId, SimpMessageHeaderAccessor  headerAccessor, UsernamePasswordAuthenticationToken token)
    {
        UserDto user = (UserDto)token.getPrincipal();

        simpMessageSendingOperations.convertAndSend("/sub/room" + roomId, user.getName() +  " : " + message.getContent());

        return user.getName() +  " : " + message.getContent();
    }

}
