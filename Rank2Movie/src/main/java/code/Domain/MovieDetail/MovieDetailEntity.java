package code.Domain.MovieDetail;


import code.Domain.Movie.MovieEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="movieDetail")
@Data
@Getter
@Setter
public class MovieDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String movieDetailNm;
    //영화
    @OneToOne(mappedBy = "movieDetailEntity")
    private MovieEntity movieEntity;
    //    심의등급
    private String watchGradeNm;
    //    상영시간
    private String showTm;
}
