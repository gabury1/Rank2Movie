package code.Domain.Movie;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movie")
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
//    심의등급
    private String watchGradeNm;
//    상영시간
    private String showTm;

}
//    상영시간
//    private String showTm;
//    심의등급
//    private String watchGradeNm;
//    누적 매출액
//    private String salesAcc;
//    당일 관객수
//    private String audiCnt;
//    관객수 증감
//    private String audiInten;
