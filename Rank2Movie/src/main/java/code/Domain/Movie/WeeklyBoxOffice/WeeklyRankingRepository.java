package code.Domain.Movie.WeeklyBoxOffice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WeeklyRankingRepository extends JpaRepository<WeeklyRankingEntity, Integer>
{

    @Query(value = "SELECT * FROM weekly_box_office order by rank_no asc;", nativeQuery = true)
    public List<WeeklyRankingEntity> findAll();

}
