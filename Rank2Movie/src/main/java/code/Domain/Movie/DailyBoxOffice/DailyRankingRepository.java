package code.Domain.Movie.DailyBoxOffice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyRankingRepository extends JpaRepository<DailyRankingEntity, Integer>
{
    
}
