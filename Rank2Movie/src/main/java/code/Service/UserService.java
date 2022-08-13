package code.Service;

import code.DTO.UpdateDto;
import code.DTO.UserDto;
import code.DTO.SignupDto;
import code.Domain.User.UserEntity;
import code.Domain.User.UserRepository;
import code.Domain.User.UserLikeRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

    // 유저 생성
    @Transactional
    public String createUser(SignupDto user) {
        try {
            userRepository.save(user.toEntity());
        } catch (RuntimeException e) {

            // 오류 발생 시, 닉네임 중복 문제
            return "닉네임이 중복되었습니다.";
        }

        return "success";
    }

    //ID 체크에 사용되는 매서드
    public Boolean availableId(String id) {
        UserEntity user = userRepository.findById(id);
        // 쓸 수 있는 아이디라면 true, 아니라면 false
        return user == null;
    }

    // 스프링 시큐리티에서 UserDto를 받아갈때 쓰는 메서드
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        UserEntity user = userRepository.findById(userId); // 일치하는게 없으면 그냥 null이 들어가도 상관 없다.

        return new UserDto(user, Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())));
    }

    // 로그인 확인용 매서드
    public String logincheck(String id, String pw) {
        if (id.equals("")) return "ID를 입력해주세요.";
        if (pw.equals("")) return "비밀번호를 입력해주세요.";

        UserEntity user = userRepository.findById(id);
        if (user == null) return "일치하는 ID가 존재하지 않습니다.";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(pw, user.getUserPassword()))
            return "비밀번호가 다릅니다.";

        return "success";
    }

    // Read, 유저 상세보기 정보를 보내주는 매서드
    public String userDetail(Long userNo) {
        JSONObject object = new JSONObject();
        UserEntity user = userRepository.findByUserNo(userNo);
        if (user == null) return object.toString(); // 없으면 그냥 반환

        // JSON에 데이터 삽입 + 추가적으로 유저 평판도 넣어줘야 함.
        object.put("userNo", user.getUserNo());
        object.put("userName", user.getUserName());
        object.put("userId", user.getUserId());
        object.put("userDetail", user.getUserDetail());

        // 현재 유저의 번호와 요청 받은 번호가 같다면 true, 아니면 false
        // 옵셔널이라 이렇게 했는데.. 흠,,, 더 좋은 방법이 떠오르면 고쳐보겠다.
        Optional<UserDto> dto = nowUser();
        if(dto.isPresent())
        {
            if(dto.get().getNo() == userNo) object.put("owner", "true");
            else object.put("owner", "false");
        }
        else object.put("owner", "false");

        return object.toString();
    }

    // Update, 유저 정보 수정
    public String update(UpdateDto user) {

        try
        {
            Long no = user.getNo();
            UserEntity entity = userRepository.findByUserNo(no);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if(!encoder.matches(user.getPw(), entity.getUserPassword())) return "비밀번호가 다릅니다.";
            user.updateEntity(entity);
            userRepository.save(entity);

        } catch (Exception e) {
            return "사용되고 있는 ID나 닉네임일 수 있습니다.";
        }

        return "success";
    }

    //Delete, 유저 탈퇴 처리
    public String delete(Long no, String pw)
    {
        UserEntity user = userRepository.findByUserNo(no);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(pw, user.getUserPassword())) return "패스워드가 틀렸습니다!";

        userRepository.delete(user);
        return "success";
    }

    // 현재 로그인된 유저의 정보를 UserDto로 반환, 로그인 상태가 아니라면 null 반환.
    public Optional<UserDto> nowUser() {
        SecurityContextHolder securityContextHolder = new SecurityContextHolder();

        // 1. 인증 객체 호출
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        // 2. 인정 정보 객체 호출
        Object principal = authentication.getPrincipal();
        if(principal.equals("anonymousUser")) return Optional.ofNullable(null);
        else return Optional.of((UserDto) principal);
    }



}

