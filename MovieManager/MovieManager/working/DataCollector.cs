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

        const String kobisKey = "b6bab379f60865cc96df750bc299b0db";
        const String movieDetailApi = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?";

        public String getMovieDetail(String movieCode)
        {

            String url = movieDetailApi + "key=" + kobisKey + "&movieCd=20227370";

            WebRequest request = WebRequest.Create(url);
            request.Method = "GET";
            request.ContentType = "application/json";

            using (WebResponse response = request.GetResponse())
            using (Stream dataStream = response.GetResponseStream())
            using (StreamReader reader = new StreamReader(dataStream))
            {

                return reader.ReadToEnd();
            }

        }




    }
}
