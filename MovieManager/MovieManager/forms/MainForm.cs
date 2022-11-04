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
        UpdateForm updateForm;



        // �̹��� ����
        public bool loginable = false;

        public String defaultImageUrl = String.Empty;
        public String id = String.Empty;
        public String pw = String.Empty;


        public MainForm()
        {
            InitializeComponent();

            Console.WriteLine("���α׷� ����!");

            loadForm = new LoadForm(this);
            updateForm = new UpdateForm(this);

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
                if (defaultImageUrl == String.Empty)
                {
                    MessageBox.Show("���� �ε带 �ϱ� ���� ����Ʈ �̹��� URL�� �������ּ���.");
                    return;
                }

                loadForm.Show();
            };

            // ���� ������Ʈ �� ����
            btnUpdate.Click += (object? sender, EventArgs e) =>
            {
                if (defaultImageUrl == String.Empty)
                {
                    MessageBox.Show("���� ������Ʈ�� �ϱ� ���� ����Ʈ �̹��� URL�� �������ּ���.");
                    return;
                }

                updateForm.Show();
            };

            // �̹��� ���� ������ �������ش�.
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