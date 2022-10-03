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

            btnStart.Click += BtnStart_Click;
            btnWeekly.Click += BtnWeekly_Click;
            btnDaily.Click += BtnDaily_Click;

        }

        private async void BtnStart_Click(object sender, EventArgs e)
        {
            String yearTxt = txtYear.Text;
            String howManyTxt = txtHowMany.Text;

            Button b = (Button)sender;

            if ( yearTxt == String.Empty)
            {
                MessageBox.Show("년도를 입력해주세요");
                return;
            }
                
            if(Convert.ToInt32(yearTxt) < 1000 || DateTime.Now.Year < Convert.ToInt32(yearTxt))
            {
                MessageBox.Show("년도를 다시 입력해주시겠어요?");
                return;
            }

            b.Enabled = false;

            if(txtHowMany.Text == String.Empty)
            { 
                await infoLoad(txtYear.Text);
            }
            else
            {
                if (Convert.ToInt32(howManyTxt) < 0)
                {
                    MessageBox.Show("총 개수는 음수가 될 수 없습니다.");
                    return;

                }

                await infoLoad(txtYear.Text, Convert.ToInt32 (txtHowMany.Text));
            }

            b.Enabled = true;

        }

        private async void BtnWeekly_Click(object sender, EventArgs e)
        {
            Button b = (Button)sender;
            b.Enabled = false;

            LogView("주간 박스오피스를 불러옵니다...");
            var list = await collector.getWeeklyBoxOffices();
            LogView("주간 박스오피스 로드 성공.");

            using (var db = new MovieDbContext())
            {
                try
                {
                    LogView("주간 박스오피스를 DB에 저장합니다...");
                    db.resetWeeklyRanking();

                    await db.weeklyBoxOffice.AddRangeAsync(list);
                    
                    db.SaveChanges();
                    LogView("주간 박스오피스 저장 성공.");

                }
                catch
                {

                    LogView("주간 박스 오피스 저장 실패. 최신 영화를 불러왔는지 확인해주시겠어요?");
                }
                finally
                {

                    b.Enabled = true;
                }

            }


        }

        private async void BtnDaily_Click(object sender, EventArgs e)
        {

            Button b = (Button)sender;
            b.Enabled = false;

            LogView("일간 박스오피스를 불러옵니다...");
            var list = await collector.GetDailyBoxOffices();
            LogView("일간 박스오피스 로드 성공.");

            using (var db = new MovieDbContext())
            {
                try
                {
                    LogView("일간 박스오피스를 DB에 저장합니다...");
                    db.resetDailyRanking();

                    await db.dailyBoxOffice.AddRangeAsync(list);

                    db.SaveChanges();
                    LogView("일간 박스오피스 저장 성공.");

                }
                catch
                {

                    LogView("일간 박스오피스 저장 실패. 최신 영화를 불러왔는지 확인해주시겠어요?");
                }
                finally
                {

                    b.Enabled = true;
                }

            }

        }

        async Task infoLoad(String year)
        {
            // 만약 갯수가 없다면 그냥 최대값으로 지정해도 됨.
            await infoLoad(year, int.MaxValue);
        }

        async Task infoLoad(String year, int cnt)
        {
            LogView("개봉년도 " + year + "년부터의 영화리스트 불러오기 시작...");
            var loadListTask = Task.Run(() => collector.getMovieList(Convert.ToInt32(year)));
            var list = await loadListTask;
            LogView(" Total count : " + list.Count);
            LogView("개봉년도 " + year + "년부터의 영화리스트 불러오기 완료\n");

            if (cnt < list.Count) cnt = list.Count;
            LogView("영화 상세정보 불러오기 시작... Count : " + cnt);

            var loadDetailTask = Task.Run(() => collector.getMovies(list, cnt));
            List<Movie> movies = await loadDetailTask;

            LogView( "영화 상세정보 불러오기 완료\n");

            using (var db = new MovieDbContext())
            {
                LogView("영화를 데이터베이스에 저장 시작...");

                db.movies.AddRange(movies);
                db.SaveChanges();

                LogView("영화를 데이터베이스에 저장 완료 총 " + movies.Count + " 개");

            }
        }



        public void LogView(string str)
        {
            txtLog.AppendText(DateTime.Now.ToShortDateString() + " " + DateTime.Now.ToShortTimeString() + "  ::::: " + str + "\r\n");
        }

    }
}
