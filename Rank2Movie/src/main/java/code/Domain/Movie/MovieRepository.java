package code.Domain.Movie;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    MovieEntity findByMovieCd(String movieCd);
    List<MovieEntity> findAllByRating(Double rating, Sort sort);
}