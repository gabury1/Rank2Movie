package code.Controller;


import code.KobisAPI;
import code.NaverAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class MovieController {

//    @Autowired
//    private MovieService movieService;
    private final KobisAPI boa;
    private final NaverAPI nav;

    @GetMapping("/movie/test")
    public String hello(){
        boa.getMovieList(10);
        boa.getMovieDetail("20211792");
        boa.getDailyMovie();
        return "/movie/test";
    }

    @RequestMapping(value = "/KobisTest", produces = "application/text; charset=UTF-8")
    public void TestKobisAPI(HttpServletResponse response){
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
