package code.Domain.Board;

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
import org.springframework.stereotype.Component;

import code.Domain.Movie.movie.MovieEntity;
import code.Domain.User.UserEntity;
import groovy.transform.builder.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Table(name="board")
@Data @Entity
@Getter@Setter@Component
public class BoardEntity 
{
    // 번호
    @Id @Column(name="board_no")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int boardNo;
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
    @ManyToOne(fetch=FetchType.LAZY)
    private MovieEntity movie;

    // 유저 (외래키)
    @JoinColumn(name="writer_no")
    @ManyToOne(fetch=FetchType.LAZY)
    private UserEntity writer;

}
