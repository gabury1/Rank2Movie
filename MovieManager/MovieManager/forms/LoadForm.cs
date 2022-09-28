using MovieManager.database;
using MovieManager.database.models;
using MovieManager.working;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MovieManager.forms
{
    public partial class LoadForm : Form
    {

        MainForm mainForm;
        DataCollector collector = new DataCollector();

        Thread worker;

        public LoadForm(MainForm mainForm)
        {
            InitializeComponent();
            this.mainForm = mainForm;   
        }

        private void LoadForm_Load(object sender, EventArgs e)
        {
            // 꺼지면 안돼
            this.FormClosing += (object? sender, FormClosingEventArgs e) => 
            {
                e.Cancel = true;
                this.Hide();

            };

            btnStart.Click += (object? sender, EventArgs e) =>
            {
                if (txtYear.Text == "") return;
                
                infoLoad(txtYear.Text);

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
                movies.RemoveAll(s => s == null); // null 제거 ( 오류가 뜨더라도 저장은 해야하니...)
                db.movies.AddRange(movies);
                db.SaveChanges();
                Thread.Sleep(100);
                Console.WriteLine(DateTime.Now + "   :::::: " + " 데이터베이스에 저장 완료\n");

            }

        }


    }
}
