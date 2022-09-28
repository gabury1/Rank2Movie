package code.Domain.Movie.DailyBoxOffice;


import lombok.*;
import javax.persistence.*;

import code.Domain.Movie.movie.MovieEntity;

import java.util.List;


@Entity @Builder
@Table(name="daily_box_office")
@Data @AllArgsConstructor
@NoArgsConstructor
public class DailyRankingEntity 
{   
    @Id
    @Column(name = "rank_no")
    Integer rankNo;

    @Column(name = "new_or_old")
    String newOrOld;

    @Column(name = "rank_inten")
    Integer rankInten;
    @Column(name = "audi_cnt")
    Integer audiCnt;
    @Column(name = "open_date")
    String openDate;

    @JoinColumn(name = "movie_code")
    @OneToOne
    MovieEntity movie;

}
