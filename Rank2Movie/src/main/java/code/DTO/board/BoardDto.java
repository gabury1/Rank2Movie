package code.DTO.board;

import lombok.Data;
import code.Domain.Board.BoardEntity;
import code.Domain.Movie.movie.MovieEntity;
import code.Domain.User.UserEntity;
import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class BoardDto 
{
    String boardNo;

    String title;
    String movieCode;
    String content;
    Integer rating;
    Boolean spoiler;
    String dateTime;
    
    UserEntity writer;

    public BoardEntity toEntity()
    {
        BoardEntity entity = new BoardEntity();
        entity.setTitle(title);
        entity.setContent(content);
        entity.setRating(rating);
        entity.setSpoiler(spoiler);
        entity.setDateTime(dateTime);
        entity.setViews(0);

        entity.setWriter(writer);
        MovieEntity m = new MovieEntity(); 
        m.setMovieCode(movieCode);
        entity.setMovie(m);
        
        return entity;
    }


}
