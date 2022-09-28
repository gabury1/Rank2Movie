package code.Domain.Movie.movie;


import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;


@Table(name="movie")
@Data @Entity
@Getter@Setter@Component
public class MovieEntity 
{

    @Id
    @Column(name = "movie_code")
    String movieCode;

    // 영화 정보
    @Column(name = "title_kor")
    String titleKor;
    @Column(name = "title_en")
    String titleEn;

    @Column(name = "genre")
    String genre;
    @Column(name = "actors")
    String actors;
    @Column(name = "show_time")
    String showTime;
    @Column(name = "watch_grade")
    String watchGrade;

    @Column(name = "company")
    String companyName;
    @Column(name = "director")
    String director;
    @Column(name = "product_status")
    String productStatus;
    @Column(name = "nation")
    String nation;

    @Column(name = "image_url")
    String imageUrl;
    @Column(name = "views")
    int views;
    @Column(name = "avr_rating")
    Double avrRating;



}

