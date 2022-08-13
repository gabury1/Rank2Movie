package code.Controller;

import code.DTO.user.SignupDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class MainController
{
    @RequestMapping("/")
    public String home()
    {

        return "/home";
    }

    @RequestMapping("/test/{str}")
    @ResponseBody
    public String test(@PathVariable("str") String str)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return new SignupDto().builder()
                .id("gabury1")
                .pw("em201414")
                .repw("em201414")
                .name("euns")
                .build().toEntity().toString();

    }

}
