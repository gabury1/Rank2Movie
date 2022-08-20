package code.Service;

import code.Domain.Movie.MovieEntity;
import code.Domain.Movie.MovieRepository;
import code.KobisAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Component
@Service
@RequiredArgsConstructor
public class MovieService {

    private final KobisAPI boa;
    private final MovieRepository movieRepository;


    public void showList(HttpServletResponse response){
        List<MovieEntity> movieEntityList = movieRepository.findAll();
        for(MovieEntity m : movieEntityList){
            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.getWriter().println(m);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public void showMovie(HttpServletResponse response, String movieCd){
        MovieEntity m = movieRepository.findByMovieCd(movieCd);
        Integer nowViews = m.getViews();
        if( nowViews == null){
            nowViews = 0;
        }
        m.setViews(nowViews+= 1);
        movieRepository.save(m);
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
