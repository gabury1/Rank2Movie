using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json.Linq;

// Selenium
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;

namespace MovieManager.working
{
    public class ImageLoader
    {
        private ChromeDriverService cds;
        private ChromeOptions cdo;
        private ChromeDriver driver;

        const String NaverAPI = "https://openapi.naver.com/v1/search/movie.json?";


        public ImageLoader(bool visible)
        {
            
            cds = ChromeDriverService.CreateDefaultService();
            cds.HideCommandPromptWindow = true;

            cdo = new ChromeOptions();
            cdo.AddArgument("disable_gpu");
            if (!visible) cdo.AddArgument("headless");

            //드라이버 생성
            driver = new ChromeDriver(cds, cdo);

            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(10);
            Thread.Sleep(1000);
            
        }

        public ImageLoader(bool visible, String id, String pw)
        {

            cds = ChromeDriverService.CreateDefaultService();
            cds.HideCommandPromptWindow = true;

            cdo = new ChromeOptions();
            cdo.AddArgument("disable_gpu");
            if (!visible) cdo.AddArgument("headless");

            //드라이버 생성
            driver = new ChromeDriver(cds, cdo);

            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(10);
            Thread.Sleep(1000);

            naverLogin(id, pw);

        }

        public void naverLogin(String id, String pw)
        {
            visit("https://nid.naver.com/nidlogin.login");
            Thread.Sleep(500);
            var txtId = driver.FindElement(By.Id("id"));
            var txtPw = driver.FindElement(By.Id("pw"));

            var pre = Clipboard.GetDataObject();

            Thread.Sleep(500);
            Clipboard.SetText(id);
            txtId.SendKeys(OpenQA.Selenium.Keys.Control + "v");
            Thread.Sleep(500);
            Clipboard.SetText(pw);
            txtPw.SendKeys(OpenQA.Selenium.Keys.Control + "v");
            Thread.Sleep(500);
            driver.FindElement(By.Id("log.login")).Click();

            Clipboard.SetDataObject(pre);

            Console.WriteLine("로그인 완료");

        }


        public void visit(String URL)
        {
            driver.Navigate().GoToUrl(URL);
            Thread.Sleep(100);
        }

        /// <summary>
        /// 영화의 제목과 생산년도를 입력하면 해당 영화의 NAVER영화 URL를 반환한다.
        /// 단, 네이버 영화에 등록되지 않은 영화라면 null을 반환한다.
        /// </summary>
        /// <param name="title"></param>
        /// <param name="year"></param>
        /// <returns>String 네이버 영화 URL</returns>
        public string? getNaverUrl(String title, String year)
        {
            // API URL
            String url = NaverAPI + "query=" + title + "&yearfrom=" + year + "&yearto=" + year + "&display=1";
            JObject json;

            // JSON을 리퀘스트로 받아온다.
            WebRequest request = WebRequest.Create(url);
            request.Method = "GET";
            request.ContentType = "application/json";
            request.Headers.Add("X-Naver-Client-Id", "G8CCHesbO5Y2Ml1wZ2Dp");
            request.Headers.Add("X-Naver-Client-Secret", "wdhjBqygvX");

            using (WebResponse response = request.GetResponse())
            using (Stream dataStream = response.GetResponseStream())
            using (StreamReader reader = new StreamReader(dataStream))
            {

                json = JObject.Parse(reader.ReadToEnd());
            }

            Console.WriteLine(json.ToString());
            JArray items = (JArray)json["items"];
            if (items.Count == 0) return null;
            else return items[0]["link"].ToString();
            
        }


        public String getImageUrl(String url)
        {

            visit(url);
            try{
                var element = driver.FindElement(By.XPath("//*[@id=\"content\"]/div[1]/div[2]/div[2]/a/img"));
                element.GetAttribute("src");

                return element.GetAttribute("src");

            }catch{

                return null;

            }

        }

        public void close()
        {
            cds.Dispose();
            driver.Dispose();
        }

    }
}
