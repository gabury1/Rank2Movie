package code.Service;

import code.Domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class UserService
{
    @Autowired
    UserRepository userRepository;


}
