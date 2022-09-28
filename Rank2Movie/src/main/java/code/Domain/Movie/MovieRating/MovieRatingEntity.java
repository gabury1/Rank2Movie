package code.Domain.Movie.MovieRating;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import code.Domain.Movie.movie.MovieEntity;
import code.Domain.User.UserEntity;


@Entity
@Table(name="movie_rating")
@Data @Getter @Setter
public class MovieRatingEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_no")
    private Long ratingNo;

    @Column(name="rating_point")
    private Integer ratingPoint;
    

    @ManyToOne
    @JoinColumn(name="movie_code")
    private MovieEntity movie;
    
    // 유저 고유번호
    @ManyToOne
    @JoinColumn(name="user_no")
    private UserEntity user;
}
