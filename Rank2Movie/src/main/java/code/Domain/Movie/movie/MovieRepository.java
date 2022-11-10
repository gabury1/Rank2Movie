package code.Domain.Movie.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, String> {

    // 코드로 검색
    @Query(value="SELECT * FROM movie WHERE movie_code=:code", nativeQuery = true)
    public Optional<MovieEntity> findByMovieCode(@Param("code") String code);

    // 제목 검색
    @Query(value="SELECT * FROM movie WHERE title_kor LIKE %:str%", nativeQuery = true)
    public Page<MovieEntity> pageOfTitle(@Param("str")String str, Pageable page);
    @Query(value="SELECT * FROM movie WHERE title_kor LIKE %:str% AND product_status='개봉'", nativeQuery = true)
    public Page<MovieEntity> pageOfTitleOpened(@Param("str")String str, Pageable page);
    
    // 감독 검색
    @Query(value="SELECT * FROM movie WHERE director LIKE %:str%", nativeQuery = true)
    public Page<MovieEntity> pageOfDirector(@Param("str")String str, Pageable page);
    @Query(value="SELECT * FROM movie WHERE director LIKE %:str% AND  product_status='개봉'", nativeQuery = true)
    public Page<MovieEntity> pageOfDirectorOpened(@Param("str")String str, Pageable page);

    // 배우 검색
    @Query(value="SELECT * FROM movie WHERE actor LIKE %:str%", nativeQuery = true)
    public Page<MovieEntity> pageOfActor(@Param("str")String str, Pageable page);
    @Query(value="SELECT * FROM movie WHERE actor LIKE %:str% AND product_status='개봉'", nativeQuery = true)
    public Page<MovieEntity> pageOfActorOpened(@Param("str")String str, Pageable page);

    // 장르 검색
    @Query(value="SELECT * FROM movie WHERE genre LIKE %:str%", nativeQuery = true)
    public Page<MovieEntity> pageOfGenre(@Param("str")String str, Pageable page);
    @Query(value="SELECT * FROM movie WHERE genre LIKE %:str% AND product_status='개봉'", nativeQuery = true)
    public Page<MovieEntity> pageOfGenreOpened(@Param("str")String str, Pageable page);

}