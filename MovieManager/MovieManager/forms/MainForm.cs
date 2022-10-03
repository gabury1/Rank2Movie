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

        // ���� �ε��ϴ� ��
        LoadForm loadForm;


        public MainForm()
        {
            InitializeComponent();

            Console.WriteLine("���α׷� ����!");

            loadForm = new LoadForm(this);

        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            // Ÿ�̸� ����
            mainTimer.Interval = 100;
            mainTimer.Start();

            mainTimer.Tick += (object? sender, EventArgs e) =>
            {
                btnTime.Text = DateTime.Now.ToString();

            };

            // ���� �ε� �� ����
            btnLoad.Click += (object? sender, EventArgs e) =>
            {
                loadForm.Show();
            };


        }




    }
}