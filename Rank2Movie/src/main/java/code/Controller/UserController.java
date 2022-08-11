package code.Controller;

import code.DTO.SignupDto;
import code.Domain.User.UserEntity;
import code.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/signup")
    public String signup() {
        return "/user/signup";
    }

    @RequestMapping("/login")
    public String login() {
        return "/user/login";
    }

    @PostMapping("/")
    @ResponseBody
    public String signup(@Valid SignupDto newUser, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().get(0).getDefaultMessage();
        }
        // 비밀번호 재입력 검사
        if (!newUser.pwConfirm()) return "비밀번호가 서로 다릅니다.";

        return userService.createUser(newUser);

    }

    @GetMapping("/idcheck")
    @ResponseBody
    public String idCheck(@Param("id") String id) {
        if (id.length() == 0) return "아이디를 입력해주세요.";
        else if (id.length() < 7 || 28 < id.length()) return "아이디는 7자에서 28자여야 합니다.";

        // 성공 시 success, 실패 시 실패 메시지 보냄.
        return userService.idCheck(id);
    }

    @PostMapping("/logincheck")
    @ResponseBody
    public String loginCheck(@Param("id") String id, @Param("pw") String pw)
    {

        return userService.logincheck(id, pw);
    }

}
