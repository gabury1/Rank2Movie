package code.Domain.Movie;


import code.Domain.MovieDetail.MovieDetailEntity;
import code.Domain.movieRank.MovieRankEntity;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="movieTable")
@Data
@Getter
@Setter
public class MovieEntity {

//    영화 고유번호
    @Id
    private String movieCd;
//    영화제목
    private String movieNm;
//    영화영어제목
    private String movieNmEn;
//    개봉일자
    private String openDt;
//    영화사 목록
    private String companyNm;
//    감독
    private String directors;
//    장르
    private  String genreAlt;
//    제작상태
    private String prdtStatNm;
//    제작국가
    private String repNationNm;
//    조회수
    @ColumnDefault("0")
    private Integer views;

//    영화상세정보
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "movieDetailEntity_id")
    private MovieDetailEntity movieDetailEntity;

//    영화순위
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "movieRankEntity_id")
    private MovieRankEntity movieRankEntity;

}

