package code.Service;

import code.Domain.User.UserRepository;
import code.Domain.UserLike.UserLikeEntity;
import code.Domain.UserLike.UserLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class UserService
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserLikeRepository userLikeRepository;

}
