package code.Domain.Board;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>
{
    // 전체보기 게시판에서는 스포일러가 없는 글만 보이게 할거임.
    @Query(value="SELECT * FROM board WHERE spoiler=FALSE", nativeQuery = true)
    public Page<BoardEntity> pageOfNotSpoiler(Pageable page);

    // 전체보기 게시판에서는 스포일러가 없는 글만 보이게 할거임.
    @Query(value="SELECT * FROM board WHERE movie_code=:movieCode", nativeQuery = true)
    public Page<BoardEntity> pageOfMovie(@Param("movieCode") String movieCode, Pageable page);

    @Query(value="SELECT * FROM board WHERE board_no=:no", nativeQuery = true)
    public Optional<BoardEntity> findByBoardNo(@Param("no") int no);

    
}
