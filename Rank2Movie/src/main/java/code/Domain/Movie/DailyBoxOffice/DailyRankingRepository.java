package code.Domain.Movie.DailyBoxOffice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DailyRankingRepository extends JpaRepository<DailyRankingEntity, Integer>
{
    @Query(value = "SELECT * FROM daily_box_office order by rank_no asc;", nativeQuery = true)
    public List<DailyRankingEntity> findAll();
}
