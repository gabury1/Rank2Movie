package code.Domain.User;

import code.Domain.User.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity @Builder
@Table(name = "user_reputation")
@Data @AllArgsConstructor
public class UserReputationEntity
{
    @Column(name="user_reputation_no")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long UserLikeNo;

    @JoinColumn(name = "sender_no")
    @ManyToOne
    UserEntity sender;

    @JoinColumn(name = "receiver_no")
    @ManyToOne
    UserEntity receiver;

    @JoinColumn(name = "reputation")
    int reputation;

}
