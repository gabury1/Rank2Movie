package code;

import code.Domain.Movie.MovieEntity;
import code.Domain.Movie.MovieRepository;
import code.Domain.MovieDetail.MovieDetailEntity;
import code.Domain.MovieDetail.MovieDetailRepository;
import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@Component
@RequiredArgsConstructor
public class KobisAPI {

    private final MovieRepository movieRepository;
    private final MovieDetailRepository movieDetailRepository;

    //API 키 변수로 선언
    final String KEY = "b6bab379f60865cc96df750bc299b0db";

    //API 객체 생성
    KobisOpenAPIRestService kobis = new KobisOpenAPIRestService(KEY);

    
    //영화 리스트 가져와 DB에 저장
    public String getMovieList(int page){

        int pageCount =1;
        int entityCount = 0;
        String movieAPIList = "";
        JSONObject movieList = new JSONObject();
        JSONParser jsonParser = new JSONParser();

        try{
            //몇 페이지만큼의 데이터를 가져올 것인지 설정
            while (pageCount <= page) {
                //영화 목록 한 페이지 전체 가져오기기
                String[] tmp = new String[1];
                movieAPIList = kobis.getMovieList(true, Integer.toString(pageCount), "100", "", "", "", "", "", "", "", tmp);
                Object obj = jsonParser.parse(movieAPIList);
                JSONObject jsonObject = (JSONObject) obj;
                JSONObject movieListResult = (JSONObject) jsonObject.get("movieListResult");

                JSONArray parsed_Boxoffice = (JSONArray) movieListResult.get("movieList");
                for (int i = 0; i < parsed_Boxoffice.size(); i++) {
                    //해당 페이지 속 각각의 영화의 데이터를 movie에 대입
                    JSONObject movie = (JSONObject) parsed_Boxoffice.get(i);
                        try {
                            MovieEntity dailyMovie = new MovieEntity();
                            dailyMovie.setMovieNm(movie.get("movieNm").toString());
                            dailyMovie.setMovieCd(movie.get("movieCd").toString());
                            dailyMovie.setMovieNmEn(movie.get("movieNmEn").toString());
                            dailyMovie.setOpenDt(movie.get("openDt").toString());
                            dailyMovie.setCompanyNm(movie.get("companys").toString());
                            dailyMovie.setDirectors(movie.get("directors").toString());
                            dailyMovie.setGenreAlt(movie.get("genreAlt").toString());
                            dailyMovie.setPrdtStatNm(movie.get("prdtStatNm").toString());
                            dailyMovie.setRepNationNm(movie.get("repNationNm").toString());
                            movieList.put(dailyMovie.getMovieCd() , dailyMovie.getMovieNm());
                            movieRepository.save(dailyMovie);
                            entityCount++;
                        } catch (NullPointerException e) {
                            System.out.println(e);
                        }
                    }
                System.out.println("now parsing " + pageCount + "page");
                pageCount++;
                System.out.println(movieList);
                }
        }catch(Exception e) {
            System.out.println(e);
        }
        //몇 개의 페이지를 읽었는지 JSON에 기록
        movieList.put("count" , (Integer.toString(pageCount - 1) + "개의페이지에서 " + entityCount + "개의영화를읽어왔습니다.\n"));
        return movieList.toString();
    }



    //개별 영화 상세 데이터 가져와 DB에 저장
    public String getMovieDetail(String movieCode) {
        JSONObject movieDetail = new JSONObject();
        MovieEntity movieEntity = movieRepository.findByMovieCd(movieCode);
        MovieDetailEntity movieDetailEntity = new MovieDetailEntity();
        movieEntity.setMovieDetailEntity(movieDetailEntity);
        //여기서부터 작업하면 됨. movieEntity에 movieDetailEntity를 할당할 방법 고안
        System.out.println("movie는 " + movieEntity);

        try {
            JSONParser jsonParser = new JSONParser();
            String dailyMovies = kobis.getMovieInfo(true, movieCode);
            Object obj = jsonParser.parse(dailyMovies);
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println("jsonObject는 "+jsonObject);
            JSONObject movieDetailResult = (JSONObject) jsonObject.get("movieInfoResult");
            movieDetail = (JSONObject) movieDetailResult.get("movieInfo");
            System.out.println("movieDetail는 "+movieDetail);
            movieDetailEntity.setShowTm(movieDetail.get("showTm").toString());
            System.out.println("showTm은 "+movieDetail.get("showTm").toString());
            JSONArray audit = (JSONArray) movieDetail.get("audits");
            JSONObject tmp = (JSONObject) audit.get(0);
            System.out.println(tmp);
            movieDetailEntity.setWatchGradeNm(tmp.get("watchGradeNm").toString());
            System.out.println("movie는 " + movieEntity);
            System.out.println("movieDetail는 "+movieDetail);
            movieRepository.save(movieEntity);
            movieDetailRepository.save(movieDetailEntity);
        } catch (Exception e) {
            System.out.println(e);
        }
        return movieDetail.toString();
    }


    //일별 박스오피스 데이터 가져오기
    public void getDailyMovie() {

        try {

            JSONParser jsonParser = new JSONParser();

            //전날 데이터 가져오기 (당일은 불가)
            LocalDateTime time = LocalDateTime.now().minusDays(1);
            String targetDt = time.format(DateTimeFormatter.ofPattern("yyyMMdd"));

            String dailyMovies = kobis.getDailyBoxOffice(true, targetDt, "10", "", "", "");
            Object obj = jsonParser.parse(dailyMovies);
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject dailyMovieResult = (JSONObject) jsonObject.get("boxOfficeResult");

            JSONArray parsed_dailyMovie = (JSONArray) dailyMovieResult.get("dailyBoxOfficeList");
            for (int i = 0; i < parsed_dailyMovie.size(); i++) {
                //해당 페이지 속 각각의 영화의 데이터를 movie에 대입
                JSONObject movie = (JSONObject) parsed_dailyMovie.get(i);
                        //DB등록은 추후 사용이 결정되면 구현
//                    try {
//                        MovieEntity dailyMovie = new MovieEntity();
//                        dailyMovie.setMovieNm(movie.get("movieNm").toString());
//                        dailyMovie.setMovieCd(movie.get("movieCd").toString());
//                        dailyMovie.setMovieNmEn(movie.get("movieNmEn").toString());
//                        dailyMovie.setOpenDt(movie.get("openDt").toString());
//                        dailyMovie.setCompanyNm(movie.get("companys").toString());
//                        dailyMovie.setDirectors(movie.get("directors").toString());
//                        dailyMovie.setGenreAlt(movie.get("genreAlt").toString());
//                        dailyMovie.setPrdtStatNm(movie.get("prdtStatNm").toString());
//                        dailyMovie.setRepNationNm(movie.get("repNationNm").toString());
//                        movieRepository.save(dailyMovie);
//                    } catch (NullPointerException e) {
//                        System.out.println(e);
//                    }
                }


        } catch (Exception e) {
            System.out.println(e);
        }
    }





}
