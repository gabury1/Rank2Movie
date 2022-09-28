using Microsoft.EntityFrameworkCore;
using MovieManager.database;
using MovieManager.database.models;
using MovieManager.working;

namespace MovieManager
{
    public partial class MainForm : Form
    {
        DataCollector collector = new DataCollector();

        System.Windows.Forms.Timer t = new System.Windows.Forms.Timer();

        public MainForm()
        {
            InitializeComponent();

            Console.WriteLine("프로그램 시작!");

        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            btnLoad.Click += (object sender, EventArgs e) =>
            {
                if (txtYear.Text == "") return;
                infoLoad(txtYear.Text);

            };

            t.Interval = 1000;
            t.Start();
            
            t.Tick += (object? sender, EventArgs e) =>
            {
                btnTime.Text = DateTime.Now.ToString();

            };

        }

        void infoLoad(String year)
        {
            Console.WriteLine(DateTime.Now + "   :::::: 개봉년도 " + year + "년부터의 영화리스트 불러오기 시작...");
            var list = collector.getMovieList(Convert.ToInt32(year));
            Console.WriteLine("Total count : " + list.Count);
            Console.WriteLine(DateTime.Now + "   :::::: 개봉년도 " + year + "년부터의 영화리스트 불러오기 완료\n");

            using (var db = new MovieDbContext())
            {
                // 20227370
                // 20124079
                List<Movie> movies = new List<Movie>();
                Console.WriteLine(DateTime.Now + "   :::::: " + " 영화 상세정보 불러오기 시작...");
                Thread.Sleep(100);
                list.ForEach(s => movies.Add(collector.getMovieDetail(s)));
                Thread.Sleep(100);
                Console.WriteLine(DateTime.Now + "   :::::: " + " 영화 상세정보 불러오기 완료\n");

                Console.WriteLine(DateTime.Now + "   :::::: " + " 데이터베이스에 저장 시작...");
                Thread.Sleep(100);
                movies.RemoveAll(s => s == null);
                db.movies.AddRange(movies);
                db.SaveChanges();
                Thread.Sleep(100);
                Console.WriteLine(DateTime.Now + "   :::::: " + " 데이터베이스에 저장 완료\n");

            }

        }


    }
}