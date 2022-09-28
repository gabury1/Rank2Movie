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

            Console.WriteLine("���α׷� ����!");

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
            Console.WriteLine(DateTime.Now + "   :::::: �����⵵ " + year + "������� ��ȭ����Ʈ �ҷ����� ����...");
            var list = collector.getMovieList(Convert.ToInt32(year));
            Console.WriteLine("Total count : " + list.Count);
            Console.WriteLine(DateTime.Now + "   :::::: �����⵵ " + year + "������� ��ȭ����Ʈ �ҷ����� �Ϸ�\n");

            using (var db = new MovieDbContext())
            {
                // 20227370
                // 20124079
                List<Movie> movies = new List<Movie>();
                Console.WriteLine(DateTime.Now + "   :::::: " + " ��ȭ ������ �ҷ����� ����...");
                Thread.Sleep(100);
                list.ForEach(s => movies.Add(collector.getMovieDetail(s)));
                Thread.Sleep(100);
                Console.WriteLine(DateTime.Now + "   :::::: " + " ��ȭ ������ �ҷ����� �Ϸ�\n");

                Console.WriteLine(DateTime.Now + "   :::::: " + " �����ͺ��̽��� ���� ����...");
                Thread.Sleep(100);
                movies.RemoveAll(s => s == null);
                db.movies.AddRange(movies);
                db.SaveChanges();
                Thread.Sleep(100);
                Console.WriteLine(DateTime.Now + "   :::::: " + " �����ͺ��̽��� ���� �Ϸ�\n");

            }

        }


    }
}