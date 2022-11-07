package code.Domain.Movie.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, String> {
    
    @Query(value="%:str%", nativeQuery = true)
    public Page<MovieEntity> pageOf(@Param("str")String str, Pageable page);
    
    
}