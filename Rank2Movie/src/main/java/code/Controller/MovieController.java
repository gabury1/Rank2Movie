package code.Controller;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import code.DTO.search.SearchDto;
import code.Service.*;
import groovyjarjarpicocli.CommandLine.ExecutionException;

@Controller
@RequestMapping("/movie")
public class MovieController 
{
    @Autowired
    MovieService movieService;

    @GetMapping("/movies")
    public String movies()
    {

        return "/movie/list";
    }

    @GetMapping("/detail")
    public String detail()
    {

        return "/movie/detail";
    }

    @GetMapping("/")
    public void getDetail(String code, HttpServletResponse response)
    {
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(movieService.getMovie(code));

        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    @GetMapping("/movie_list")
    public void movieList(SearchDto searchDto, HttpServletResponse response)
    {

        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(movieService.getMovieList(searchDto));
        }
        catch(Exception e)
        {

            System.out.println(e.getMessage());
        }

    }

    @GetMapping("/movie_selector")
    public void movieSelector(String str, Integer page, HttpServletResponse response)
    {
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(movieService.getMovieSelector(str, page));
        }catch(Exception e){

            System.out.println(e.getMessage());
        }
        
    }

    @GetMapping("/daily_ranking")
    public void dailyRanking(HttpServletResponse response)
    {
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(movieService.getDailyRanking());
        }
        catch(Exception e)
        {

            System.out.println(e.getMessage());
        }

    }

    @GetMapping("/weekly_ranking")
    public void weekly_ranking(HttpServletResponse response)
    {
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(movieService.getWeeklyRanking());
        }
        catch(Exception e)
        {

            System.out.println(e.getMessage());
        }

    }

    @GetMapping("/test")
    public String test(){
        return "/main/board";
    }

}
