package code.Domain.Board;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import code.Domain.Movie.movie.MovieEntity;
import code.Domain.User.UserEntity;
import groovy.transform.builder.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name="board")
@Data @Entity
@Getter@Setter@Component
@NoArgsConstructor
public class BoardEntity 
{
    // 번호
    @Id @Column(name="board_no")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long boardNo;
    // 작성일
    @Column(name="date_time")
    private String dateTime;

    // 제목
    @Column(name="title")
    private String title;
    // 내용
    @Column(name="content")
    private String content;
    // 별점 (유저가 해당 영화에 몇개의 별점을 주었는가??)
    @Column(name="rating")
    private Integer rating;
    // 스포일러 有無(스포일러가 있다면 전체 게시물 화면에는 나오지 않게 하도록)
    @Column(name="spoiler")
    private Boolean spoiler;

    // 조회수
    private int views;

    // 영화 (외래키)
    @JoinColumn(name="movie_code")
    @ManyToOne(fetch=FetchType.EAGER)
    private MovieEntity movie;

    // 유저 (외래키)
    @JoinColumn(name="writer_no")
    @ManyToOne(fetch=FetchType.EAGER)
    private UserEntity writer;

    // 댓글 (이 게시물의 댓글)
    @JoinColumn(name="board_no")
    @OneToMany(fetch=FetchType.EAGER)
    private List<CommentEntity> comments;

    public BoardEntity(Long no)
    {
        boardNo = no;
    }

    public JSONObject toJsonForList()
    {
        JSONObject object = new JSONObject();

        object.put("boardNo", boardNo);
        object.put("moviePoster", movie.getImageUrl());
        object.put("title", title);
        object.put("views", views);
        object.put("content", content);
        object.put("comments", comments.size());
        object.put("writer", writer.getUserName());
        object.put("date", dateTime);
        
        return object;
    }

    public JSONObject toJsonForDetail()
    {
        JSONObject object = new JSONObject();

        JSONObject movieObject = new JSONObject();
        movieObject.put("title", movie.getTitleKor());
        movieObject.put("imageUrl", movie.getImageUrl());
        movieObject.put("code", movie.getMovieCode());

        object.put("title", title);
        object.put("views", views);
        object.put("content", content);
        object.put("date", dateTime);
        object.put("rating", rating);
        object.put("comments", comments.size());

        // 영화 
        object.put("movie", movieObject);
        // 작성자
        object.put("writer", writer.getUserName());

        return object;

    }


}
