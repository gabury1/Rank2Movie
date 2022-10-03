using Microsoft.EntityFrameworkCore;
using MovieManager.database;
using MovieManager.database.models;
using MovieManager.forms;
using MovieManager.working;

namespace MovieManager
{
    public partial class MainForm : Form
    {
        DataCollector collector = new DataCollector();

        System.Windows.Forms.Timer mainTimer = new System.Windows.Forms.Timer();

        // 정보 로드하는 폼
        LoadForm loadForm;


        public MainForm()
        {
            InitializeComponent();

            Console.WriteLine("프로그램 시작!");

            loadForm = new LoadForm(this);

        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            // 타이머 설정
            mainTimer.Interval = 100;
            mainTimer.Start();

            mainTimer.Tick += (object? sender, EventArgs e) =>
            {
                btnTime.Text = DateTime.Now.ToString();

            };

            // 정보 로드 폼 띄우기
            btnLoad.Click += (object? sender, EventArgs e) =>
            {
                loadForm.Show();
            };


        }




    }
}