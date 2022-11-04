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
        UpdateForm updateForm;



        // 이미지 관련
        public bool loginable = false;

        public String defaultImageUrl = String.Empty;
        public String id = String.Empty;
        public String pw = String.Empty;


        public MainForm()
        {
            InitializeComponent();

            Console.WriteLine("프로그램 시작!");

            loadForm = new LoadForm(this);
            updateForm = new UpdateForm(this);

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
                if (defaultImageUrl == String.Empty)
                {
                    MessageBox.Show("정보 로드를 하기 전에 디폴트 이미지 URL을 설정해주세요.");
                    return;
                }

                loadForm.Show();
            };

            // 정보 업데이트 폼 띄우기
            btnUpdate.Click += (object? sender, EventArgs e) =>
            {
                if (defaultImageUrl == String.Empty)
                {
                    MessageBox.Show("정보 업데이트를 하기 전에 디폴트 이미지 URL을 설정해주세요.");
                    return;
                }

                updateForm.Show();
            };

            // 이미지 관련 설정을 저장해준다.
            btnImage.Click += (object? sender, EventArgs e) => 
            {
                defaultImageUrl = txtImage.Text;

                loginable = chkLogin.Checked;
                id = txtId.Text;
                pw = txtPw.Text;
     
            };

            chkLogin.CheckedChanged += (object sender, EventArgs e) =>
            {
                CheckBox c = (CheckBox)sender;
                pnlLogin.Enabled = c.Checked;

                txtId.Text = String.Empty;
                txtPw.Text = String.Empty;

            };

            chkEnable.CheckedChanged += (object? sender, EventArgs e) =>
            {

                boxImage.Enabled = !chkEnable.Checked;
            };

            
        }

        public String getDefaultUrl()
        {

            return defaultImageUrl;
        }

    }
}