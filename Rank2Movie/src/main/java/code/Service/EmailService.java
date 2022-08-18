package code.Service;

import code.DTO.mail.MailDto;
import code.Domain.Mail.AuthTokenEntity;
import code.Domain.Mail.AuthTokenRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @RequiredArgsConstructor @AllArgsConstructor
public class EmailService
{
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private AuthTokenRepository authTokenRepository;
    private static final String FROM_ADDRESS = "senderacc@naver.com"; // 새로 만든 깡통 계정
    private static final String domainName = "http://localhost:8080";

    // 인증 요청이 오면, 토큰을 생성한다음에 보내준다.
    public String authRequest(String email)
    {
        Optional<AuthTokenEntity> optional = authTokenRepository.findByEmail(email);

        AuthTokenEntity entity = optional.orElseGet(()->new AuthTokenEntity(email));
        entity.updateTime(); // 갱신은 시간을 추가해줘야함.

        authTokenRepository.save(entity);

        MailDto mail = MailDto.builder()
                .title("환영합니다! " + email + "님!")
                .message("지옥불을 받아라! " + email + "! \n" + domainName + "/user/email-auth?token=" + entity.getId())
                .address(email).build();

        try{
            mailSend(mail);
        }
        catch (MailException m)
        {
            return m.getMessage();
        }

        return entity.toString();
    }


    public void mailSend(MailDto mailDto) throws MailException {

        // 잘못된 이메일이라면 오류가 난다.

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);

    }

}
