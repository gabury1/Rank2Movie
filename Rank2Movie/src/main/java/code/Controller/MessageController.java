package code.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import code.DTO.letter.MessageDto;
import code.Service.UserService;

@Controller
public class MessageController 
{
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @Autowired
    private UserService userService;

    @MessageMapping("/room")
    public void message(MessageDto message)
    {
        
        simpMessageSendingOperations.convertAndSend("/sub/room" + message.getRoomId(), message.getContent());
    }
}
