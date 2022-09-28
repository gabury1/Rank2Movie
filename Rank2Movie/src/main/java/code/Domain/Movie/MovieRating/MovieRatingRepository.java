package code.Domain.Movie.MovieRating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MovieRatingRepository extends JpaRepository<MovieRatingEntity, Long> 
{

    
}