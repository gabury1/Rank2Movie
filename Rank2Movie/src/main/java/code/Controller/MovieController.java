package code.Controller;


import code.KobisAPI;
import code.NaverAPI;
import code.Service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final KobisAPI kobis;
    private final NaverAPI nav;

    @RequestMapping(value = "/movie/list", produces = "application/text; charset=UTF-8")
    public String hello(HttpServletResponse response){
        movieService.showList(response);
        return "/movie/test";
    }

    @RequestMapping(value = "/movie/19920077", produces = "application/text; charset=UTF-8")
    public String movie(HttpServletResponse response){
        movieService.showMovie(response, "19920077");
        return "/movie/test";
    }

    @RequestMapping(value = "/movie/detail/2022B170", produces = "application/text; charset=UTF-8")
    public String getMovieDetail(){
        kobis.getMovieDetail("2022B170");
        return "movie/test";
    }

    @RequestMapping(value = "/KobisTest", produces = "application/text; charset=UTF-8")
    public String getMovieList(){
        kobis.getMovieList(10);
        return "movie/test";
    }
}
