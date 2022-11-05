package code.Service;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code.Domain.Movie.DailyBoxOffice.DailyRankingEntity;
import code.Domain.Movie.DailyBoxOffice.DailyRankingRepository;
import code.Domain.Movie.WeeklyBoxOffice.WeeklyRankingEntity;
import code.Domain.Movie.WeeklyBoxOffice.WeeklyRankingRepository;
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

    public JSONArray movieList()
    {


        return null;
    }


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
