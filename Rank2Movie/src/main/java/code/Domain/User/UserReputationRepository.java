package code.Domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReputationRepository extends JpaRepository<UserReputationEntity, Long>
{


}
