using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MovieManager.database;
using MovieManager.database.models;
using MovieManager.forms;
using MovieManager.working;

namespace MovieManager.forms
{
    public partial class UpdateForm : Form
    {
        MainForm mainForm;
        DataCollector collector;


        public UpdateForm(MainForm mainForm)
        {
            InitializeComponent();
            this.mainForm = mainForm;

            collector = new DataCollector();

        }

        private void UpdateForm_Load(object sender, EventArgs e)
        {
            // 꺼지면 안돼
            this.FormClosing += (object? sender, FormClosingEventArgs e) =>
            {
                e.Cancel = true;
                this.Hide();
            };

            btnStart.Click += (object sender, EventArgs e) =>
            {
                if(cbHours.SelectedIndex == -1 || cbMinute.SelectedIndex == -1)
                {
                    LogView("시간을 설정해주세요.");
                    return;
                }

               Button b = (Button) sender;

                if(timer.Enabled)
                {
                    LogView("업데이트 타이머 종료");
                    b.Text = "시작";
                    boxTime.Enabled = true;
                    boxUpdate.Enabled = true;
                    timer.Stop();
                }
                else
                {
                    LogView("업데이트 타이머 시작");
                    boxTime.Enabled = false;
                    boxUpdate.Enabled = false;
                    b.Text = "종료";
                    timer.Start();
                }

            };

            btnRightNow.Click += (object sender, EventArgs e) =>
            {
                update();

            };


            timer.Tick += Timer_Tick;

        }

        bool flag = true;
        private async void Timer_Tick(object? sender, EventArgs e)
        {

            // 현재 시각을 확인하고, 업데이트할까를 결정
            if(cbHours.SelectedIndex == DateTime.Now.Hour && cbMinute.SelectedIndex*5 == DateTime.Now.Minute)
            {
                if(flag == true)
                {
                    flag = false;
                    LogView("=====업데이트 시작=====");
                    await update();
                }

            }
            else
            {
                flag = true;

            }
            

        }

        private async Task update()
        {
            LogView("최신영화 리스트 불러오기 시작");
            var listTask = Task.Run(() => collector.getMovieList(DateTime.Now.Year, 0));
            var list = await listTask;
            LogView("리스트 불러오기 완료");

            LogView("영화 상세정보 불러오기 시작");
            var moviesTask = Task.Run(() => collector.getMovies(list, 100));
            var movies = await moviesTask;
            LogView("상세정보 불러오기 완료");

            
            LogView("영화 포스터 불러오기 시작");
            

            LogView("포스터 불러오기 완료");


        }

        private void movieUpdate()
        {


        }

        public void LogView(string str)
        {
            txtLog.AppendText(DateTime.Now.ToShortDateString() + " " + DateTime.Now.ToShortTimeString() + "  ::::: " + str + "\r\n");
        }

    }
}
