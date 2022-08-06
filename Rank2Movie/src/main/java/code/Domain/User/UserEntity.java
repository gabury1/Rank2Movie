package code.Domain.User;

import code.Domain.UserLike.UserLikeEntity;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity @Builder
@Table(name="user")
@Data @AllArgsConstructor
public class UserEntity
{
    @Id
    @Column(name = "user_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userNo; // 유저 번호

    @Column(name = "user_name", length = 12, unique = true)
    String userName; // 유저 이름
    @Column(name = "user_id", length = 28, unique = true)
    String userId; // 유저 아이디
    @Column(name = "user_password", length = 28)
    String userPassword; // 유저 패스워드
    @Column(name = "user_detail", length = 500)
    String userDetail; // 유저 설명

    @OneToMany(fetch=FetchType.LAZY)
    List<UserLikeEntity> reputation; // 유저 평점

}
