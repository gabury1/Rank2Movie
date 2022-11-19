package code.Service;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import code.DTO.search.SearchDto;
import code.Domain.Movie.DailyBoxOffice.DailyRankingEntity;
import code.Domain.Movie.DailyBoxOffice.DailyRankingRepository;
import code.Domain.Movie.WeeklyBoxOffice.WeeklyRankingEntity;
import code.Domain.Movie.WeeklyBoxOffice.WeeklyRankingRepository;
import code.Domain.Movie.movie.MovieEntity;
import code.Domain.Movie.movie.MovieRepository;


@Service
public class MovieService 
{
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    WeeklyRankingRepository weeklyRepository;
    @Autowired
    DailyRankingRepository dailyRepository;


    // 영화 리스트를 반환해준다.
    public JSONObject getMovieList(SearchDto dto)
    {
        JSONObject object = new JSONObject();
        Page<MovieEntity> page = null;

        if(dto.getOpened())
        {   // 개봉한 영화만을 검색합니다.
            if(dto.getKey() == "title_kor") page = movieRepository.pageOfTitleOpened(dto.getStr(), dto.toPageable());
            else if(dto.getKey() == "director") page = movieRepository.pageOfDirectorOpened(dto.getStr(), dto.toPageable());
            else if (dto.getKey() == "actor") page = movieRepository.pageOfActorOpened(dto.getStr(),  dto.toPageable());
            else if(dto.getKey() == "genre") page = movieRepository.pageOfGenreOpened(dto.getStr(), dto.toPageable());
            else page = movieRepository.pageOfTitleOpened("%", dto.toPageable());
        }
        else
        {   // 미개봉 영화까지도 검색합니다.
            if(dto.getKey() == "title_kor") page = movieRepository.pageOfTitle(dto.getStr(), dto.toPageable());
            else if(dto.getKey() == "director") page = movieRepository.pageOfDirector(dto.getStr(), dto.toPageable());
            else if (dto.getKey() == "actor") page = movieRepository.pageOfActor(dto.getStr(),  dto.toPageable());
            else if(dto.getKey() == "genre") page = movieRepository.pageOfGenre(dto.getStr(), dto.toPageable());
            else page = movieRepository.pageOfTitle("%", dto.toPageable());
        }

        JSONArray array = new JSONArray();
        for(MovieEntity movie : page)
        {
            // 각 영화를 JSON으로 바꾸어 배열에 넣습니다.
            array.put(movie.toJSON());
        }

        // 페이지 번호를 정해준다.
        int pageStart = page.getNumber()-2;
        if(pageStart < 0) pageStart = 0;
        int pageLast = page.getNumber()+2;
        if(page.getTotalPages() <= pageLast) pageLast = page.getTotalPages()-1;

        // 모든 정보를 JSON에 담아 반환합니다.
        object.put("length", array.length());
        object.put("nowPage", page.getNumber());
        object.put("pageStart", pageStart);
        object.put("pageLast", pageLast);
        object.put("movies", array);

        return object;
    }

    // 영화의 상세를 전달해준다.
    public JSONObject getMovie(String code)
    {
        JSONObject object = new JSONObject();
        try{
            MovieEntity m = movieRepository.findByMovieCode(code).orElseThrow(() -> new Exception());
            object.put("movie", m.toJSON());
            object.put("post", "게시물은 아직");

            return object;

        }catch(Exception e)
        {
            return null;
        }

    }

    // 영화 셀렉터에 들어갈 값을 전달한다.
    public JSONObject getMovieSelector(String str, Integer pageNum)
    {
        Pageable page = PageRequest.of(pageNum, 5);
        Page<MovieEntity> movies = movieRepository.pageOfSelector(str, page);

        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();

        // 각 영화 정보를 담아준다.
        for (MovieEntity movieEntity : movies) 
        {
            array.put(movieEntity.toJSON());
        }

        // 페이지 정보
        Integer pageNow = movies.getNumber();
        Integer pageTotal = movies.getTotalPages();
        
        object.put("movies", array);
        object.put("pageNow", pageNow);
        object.put("pageTotal", pageTotal);

        return object;
    }

    // 이번주의 박스오피스를 반환한다.
    public JSONArray getWeeklyRanking()
    {
        JSONArray array = new JSONArray();
        List<WeeklyRankingEntity> list = weeklyRepository.findAll();

        for(WeeklyRankingEntity r : list)
        {
            array.put(r.toJSON());
        }

        return array;
    }

    // 오늘의 박스오피스를 반환한다.
    public JSONArray getDailyRanking()
    {
        JSONArray array = new JSONArray();
        List<DailyRankingEntity> list = dailyRepository.findAll();

        for(DailyRankingEntity r : list)
        {
            array.put(r.toJSON());
        }

        return array;
    }

    
}
