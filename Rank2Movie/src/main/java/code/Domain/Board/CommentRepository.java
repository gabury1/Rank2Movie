package code.Domain.Board;

import javax.xml.stream.events.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> 
{
    @Query(value="SELECT * FROM comment WHERE board_no=:no", nativeQuery=true)
    public Page<CommentEntity> findByBoardNo(@Param("no") Long boardNo, Pageable pageable);
}
