package code.Domain.Movie.movie;


import com.sun.istack.NotNull;

import code.Domain.Board.BoardEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.json.JSONObject;
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
    @Column(name = "actor")
    String actor;
    @Column(name = "show_time")
    int showTime;
    @Column(name = "watch_grade")
    String watchGrade;

    @Column(name = "company")
    String companyName;
    @Column(name = "director")
    String director;
    @Column(name = "product_status")
    String productStatus;
    @Column(name = "product_year")
    String productYear;
    @Column(name = "nation")
    String nation;

    @Column(name = "image_url")
    String imageUrl;
    @Column(name = "views")
    int views;
    @Column(name = "avr_rating")
    Double avrRating;

    @JoinColumn(name="movie_code    ")
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    List<BoardEntity> boards;

    public JSONObject toJSON()
    {
        JSONObject object = new JSONObject();

        object.put("movieCode", movieCode);
        object.put("titleKor", titleKor);
        object.put("titleEn", titleEn);
        object.put("genre", genre);
        object.put("actor", actor);
        object.put("showTime", showTime);
        object.put("watchGrade", watchGrade);

        object.put("company", companyName);
        object.put("director", director);
        object.put("productStatus", productStatus);
        object.put("productYear", productYear);
        object.put("nation", nation);

        object.put("imageUrl", imageUrl);
        object.put("views", views);
        if(avrRating == null) object.put("avrRating", "no repute");
        else object.put("avrRating", avrRating);  
        return object;

    }


}

