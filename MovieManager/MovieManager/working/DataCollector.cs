using MovieManager.database;
using MovieManager.database.models;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace MovieManager.working
{

    public class DataCollector
    {
        #region variable
        // 영화진흥원 (KOBIS에서 발급 받은 키)
        const String kobisKey = "b6bab379f60865cc96df750bc299b0db";


        // 영화 상세정보를 받아오는 API주소 (영화 코드를 입력해주면 그 영화의 상세정보를 반환한다)
        const String movieDetailApi = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?";
        // 영화 리스트를 받아오는 API 주소 (영화 리스트 시작 페이지랑 페이지 당 영화 수 넣어주면 됨)
        const String movieListApi = "https://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?";

        // 영화 랭킹을 받아오는 API 주소
        const String dailyRankingApi = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?";
        const String weeklyRankingApi = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json?";
        
        #endregion

        // 영화 코드를 입력하면 영화를 모델로 바꾸어 반환한다.
        public  Movie getMovieDetail(String movieCode)
        {
            // API URL
            String url = movieDetailApi + "key=" + kobisKey + "&movieCd=" + movieCode;
            Console.WriteLine(url);
            String json = String.Empty;

            // JSON을 리퀘스트로 받아온다.
            WebRequest request = WebRequest.Create(url);
            request.Method = "GET";
            request.ContentType = "application/json";

            Console.WriteLine($"{movieCode} 시작");

            using (WebResponse response = request.GetResponse())
            using (Stream dataStream = response.GetResponseStream())
            using (StreamReader reader = new StreamReader(dataStream))
            {

                json = reader.ReadToEnd();
            }

            // JSON 파싱 시작( 잘못된 요소에 대해서는 오류가 발생할 수 있음 )
            try
            {
                
                JObject jObject = JObject.Parse(json);
                var info = jObject["movieInfoResult"]["movieInfo"];

                int cnt;

                // DB에 저장될 문자열들
                String nationStr;
                String directorStr;
                String genreStr;
                String actorStr;
                String watchGradeStr;
                String companyStr;
                String openDate;

                // 장르 문자열 생성
                genreStr = String.Empty;
                foreach(var t in info["genres"])
                {
                    genreStr += t["genreNm"].ToString() + ", ";

                }
                // 마지막 쉼표와 공백 제거
                if (genreStr != String.Empty) genreStr = genreStr.Remove(genreStr.Length - 2, 2);


                // 배우 문자열 생성 (5명까지만 저장하겠음)
                cnt = 0;
                actorStr = String.Empty;
                
                foreach(var t in info["actors"])
                {
                    if (4 < cnt) break;
                    cnt++;
                    actorStr += t["peopleNm"].ToString() + ", ";

                }
                if(actorStr != String.Empty) actorStr = actorStr.Remove(actorStr.Length - 2, 2);



                // 회사 문자열 생성(회사는 3개만 저장 하겠음)
                cnt = 0;
                companyStr = String.Empty;
                
                foreach (var t in info["companys"])
                {
                    if (2 < cnt) break;
                    cnt++;
                    companyStr += t["companyNm"].ToString() + ", ";

                }
                if (companyStr != String.Empty) companyStr = companyStr.Remove(companyStr.Length - 2, 2);


                // 감독 문자열 생성(3명하면 되겠지)
                cnt = 0;
                directorStr = String.Empty;
                
                foreach (var t in info["directors"])
                {
                    if (2 < cnt) break;
                    cnt++;
                    directorStr += t["peopleNm"].ToString() + ", ";

                }
                if (directorStr != String.Empty) directorStr = directorStr.Remove(directorStr.Length - 2, 2);


                // 이용 등급 문자열 생성 
                cnt = 0;
                watchGradeStr = String.Empty;

                foreach (var t in info["audits"])
                {
                    if (0 < cnt) break;
                    cnt++;
                    watchGradeStr += t["watchGradeNm"].ToString();

                }

                // 제작 국가
                cnt = 0;
                nationStr = String.Empty;

                foreach (var t in info["nations"])
                {
                    if (0 < cnt) break;
                    cnt++;
                    nationStr += t["nationNm"].ToString();
                }

                Movie m = new Movie()
                {
                    movieCode = movieCode,
                    titleKor = info["movieNm"].ToString(),
                    titleEn = (String)info["movieNmEn"],

                    productStatus = info["prdtStatNm"].ToString(),
                    productYear = info["prdtYear"].ToString(),
                    showTime = Convert.ToInt32(info["showTm"]),
                    openDate = info["openDt"].ToString(),


                    director = directorStr,
                    genre = genreStr,
                    actor = actorStr,
                    watchGrade = watchGradeStr,
                    companyName = companyStr,
                    nation = nationStr,

                    imageUrl = "default",
                    views = 0
                    
                };

                Console.WriteLine($"{movieCode} 끝");

                return m;
            }
            catch(Exception e)
            {
                
                Console.WriteLine("영화 상세정보 API 사용 중 생긴 오류");
                Console.WriteLine("오류가 난 영화 : " + movieCode);
                Console.WriteLine(e.Message);

                return null;
            }

        }

        // 지정 년도부터의 영화리스트 코드를 받아온다.
        public List<String> getMovieList(int startYear, int page)
        {
            String url = movieListApi + "key=" + kobisKey + "&itemPerPage=100&openStartDt=" + startYear + "&curPage=";
            String json;
            List<String> codes = new List<String>();

            // JSON을 리퀘스트로 받아온다.
            WebRequest request = WebRequest.Create(url + page);
            request.Method = "GET";
            request.ContentType = "application/json";
            
            using (WebResponse response = request.GetResponse())
            using (Stream dataStream = response.GetResponseStream())
            using (StreamReader reader = new StreamReader(dataStream))
            {
            
                json = reader.ReadToEnd();
            }
            
            JObject jObject = JObject.Parse(json);
            Console.WriteLine(jObject);
            var info = jObject["movieListResult"]["movieList"];
            foreach(var t in info)
            {
                codes.Add((String)t["movieCd"]);
            }

            return codes;

        }


        // 지정 년도 이래의 모든 영화 코드를 받아온다.
        public async Task<List<String>> getMovieList(int startYear)
        {
            List<String> codes = new List<String>();

            for(int i=1; ; i++)
            {
                var temp = getMovieList(startYear, i);
                codes.AddRange(temp);
                if (temp.Count != 100) break;

            }


            return codes;
        }

        // 영화 모델 리스트를 받아오는 매서드
        public async Task<List<Movie>> getMovies(List<String> codeList, int cnt)
        {

            List<Movie> movies = new List<Movie>();

            using (var db = new MovieDbContext())
            {
                // 만약에 이미 저장되어있었다면 안찾아와도 됨.
                db.removeExist(codeList);
            }

            if (codeList.Count < cnt) cnt = codeList.Count;
            
            codeList = codeList.GetRange(0, cnt);

            codeList.ForEach( s => movies.Add( getMovieDetail(s)));
            movies.RemoveAll(s => s == null); // null 제거 ( 오류가 뜨더라도 저장은 해야하니...)

            return movies;

        }



        // 오늘의 주간 랭킹을 받아와 모델로 바꿔준다.
        // 매주 월요일에 갱신됨.
        public async Task<List<WeeklyBoxOffice>> getWeeklyBoxOffices()
        {
            
            List<WeeklyBoxOffice> list = new List<WeeklyBoxOffice>();

            String url = weeklyRankingApi + "key=" + kobisKey + "&weekGb=0" + "&targetDt=" + getLastSunday();
            Console.WriteLine($"weekly_url : {url}");

            String json = String.Empty;
            WebRequest request = WebRequest.Create(url);
            request.Method = "GET";
            request.ContentType = "application/json";

            using (WebResponse response = request.GetResponse())
            using (Stream dataStream = response.GetResponseStream())
            using (StreamReader reader = new StreamReader(dataStream))
            {

                json = reader.ReadToEnd();
            }

            var jobject = JObject.Parse(json);
            try
            {
                var weekly = jobject["boxOfficeResult"]["weeklyBoxOfficeList"];
                
                foreach(var j in weekly)
                {

                    String rankStr = String.Empty;
                    String rankIntenStr = String.Empty;
                    String rankOldAndNewStr = String.Empty;
                    String codeStr = String.Empty;
                    String audiCntStr = String.Empty;
                    String openDtStr = String.Empty;

                    var token = j["rnum"];
                    if (token.Type != JTokenType.Null) rankStr = token.ToString();

                    token = j["rankInten"];
                    if (token.Type != JTokenType.Null) rankIntenStr = token.ToString();

                    token = j["rankOldAndNew"];
                    if (token.Type != JTokenType.Null) rankOldAndNewStr = token.ToString();

                    token = j["movieCd"];
                    if(token.Type != JTokenType.Null) codeStr = token.ToString();

                    token = j["audiCnt"];
                    if (token.Type != JTokenType.Null) audiCntStr = token.ToString();

                    token = j["openDt"];
                    if (token.Type != JTokenType.Null) openDtStr = token.ToString();

                    WeeklyBoxOffice ranking = new WeeklyBoxOffice()
                    {
                        rankNo = Convert.ToInt32(rankStr),
                        rankInten = Convert.ToInt32(rankIntenStr),
                        newOrOld = rankOldAndNewStr,
                        audiCnt = Convert.ToInt32(audiCntStr),
                        openDate = openDtStr,
                        movieCode = codeStr

                    };

                    list.Add(ranking);
                }

            }
            catch(Exception e)
            {
                Console.WriteLine("주간 랭킹을 받아오다가 생긴 오류");
                Console.WriteLine(e.Message);
            }
            

            return list;
        }

        public async Task<List<DailyBoxOffice>> GetDailyBoxOffices()
        {
            List<DailyBoxOffice> list = new List<DailyBoxOffice>();
            
            String url = dailyRankingApi + "key=" + kobisKey + "&targetDt=" + DateTime.Now.AddDays(-1).ToShortDateString().ToString().Replace("-", "");
            Console.WriteLine($"daily_url : {url}");
            String json = String.Empty;
            WebRequest request = WebRequest.Create(url);
            request.Method = "GET";
            request.ContentType = "application/json";

            using (WebResponse response = request.GetResponse())
            using (Stream dataStream = response.GetResponseStream())
            using (StreamReader reader = new StreamReader(dataStream))
            {

                json = reader.ReadToEnd();
            }

            var jobject = JObject.Parse(json);

            try
            {
                var daily = jobject["boxOfficeResult"]["dailyBoxOfficeList"];

                foreach (var j in daily)
                {

                    String rankStr = String.Empty;
                    String rankIntenStr = String.Empty;
                    String rankOldAndNewStr = String.Empty;
                    String codeStr = String.Empty;
                    String audiCntStr = String.Empty;
                    String openDtStr = String.Empty;

                    var token = j["rnum"];
                    if (token.Type != JTokenType.Null) rankStr = token.ToString();

                    token = j["rankInten"];
                    if (token.Type != JTokenType.Null) rankIntenStr = token.ToString();

                    token = j["rankOldAndNew"];
                    if (token.Type != JTokenType.Null) rankOldAndNewStr = token.ToString();

                    token = j["movieCd"];
                    if (token.Type != JTokenType.Null) codeStr = token.ToString();

                    token = j["audiCnt"];
                    if (token.Type != JTokenType.Null) audiCntStr = token.ToString();

                    token = j["openDt"];
                    if (token.Type != JTokenType.Null) openDtStr = token.ToString();

                    DailyBoxOffice ranking = new DailyBoxOffice()
                    {
                        rankNo = Convert.ToInt32(rankStr),
                        rankInten = Convert.ToInt32(rankIntenStr),
                        newOrOld = rankOldAndNewStr,
                        audiCnt = Convert.ToInt32(audiCntStr),
                        openDate = openDtStr,
                        movieCode = codeStr

                    };
                    
                    list.Add(ranking);
                }

            }
            catch (Exception e)
            {
                Console.WriteLine("일간 랭킹을 받아오다가 생긴 오류");
                Console.WriteLine(e.Message);
            }


            return list;


        }


        public String getLastSunday()
        {
            DateTime d = DateTime.Now;
            int i = 1;
            while (d.AddDays(-1 * i).DayOfWeek.ToString() != "Sunday") i++;

            return DateTime.Now.AddDays(-1 * i).ToShortDateString().Replace("-", "");
        }


    }
}
