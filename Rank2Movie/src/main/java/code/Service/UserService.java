package code.Service;

import code.DTO.LoginDto;
import code.DTO.SignupDto;
import code.Domain.User.Role;
import code.Domain.User.UserEntity;
import code.Domain.User.UserRepository;
import code.Domain.User.UserLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserLikeRepository userLikeRepository;

    @Transactional
    public String createUser(SignupDto user) {
        try {
            userRepository.save( user.toEntity() );
        } catch (RuntimeException e) {

            throw new RuntimeException();
            //return "닉네임이 중복되었습니다.";
        }

        return "success";
    }

    public String idCheck(String id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) return "이미 존재하는 ID입니다.";

        return "success";
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Optional<UserEntity> Optional = userRepository.findById(userId);

        UserEntity user = Optional.orElse(null);

        return new LoginDto(user, Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())));
    }

    public String logincheck(String id, String pw)
    {
        if(id == "") return "ID를 입력해주세요.";
        if(pw == "") return "비밀번호를 입력해주세요.";

        Optional<UserEntity> optional = userRepository.findById(id);
        if(!optional.isPresent()) return "일치하는 ID가 존재하지 않습니다.";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(pw, optional.get().getUserPassword()))
            return "비밀번호가 다릅니다.";

        return "success";
    }

}
