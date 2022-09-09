package code.Controller;

import code.DTO.mail.MailDto;
import code.DTO.user.SignupDto;
import code.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class MainController
{
    @Autowired
    private EmailService emailService;

    @RequestMapping("/")
    public String home()
    {

        return "/main/index";
    }

    @RequestMapping("/ranking")
    public String ranking()
    {
        return "/main/ranking";
    }

    @RequestMapping("/board")
    public String board()
    {

        return "/main/board";
    }

    @RequestMapping("/chat")
    public String chat()
    {

        return "/letter/chat";
    }

}
