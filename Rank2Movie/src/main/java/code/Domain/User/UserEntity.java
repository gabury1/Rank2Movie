package code.Domain.User;

import lombok.*;
import org.hibernate.annotations.Generated;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(name="user")
@Builder
@Data @AllArgsConstructor
public class UserEntity
{
    @Id
    @Column(name = "user_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long userNo;

    @Column(name = "user_name", length = 12, unique = true)
    String userName;
    @Column(name = "user_id", length = 28, unique = true)
    String userId;
    @Column(name = "user_password", length = 28)
    String userPassword;
    @Column(name = "user_detail", length = 500)
    String userDetail;

}
