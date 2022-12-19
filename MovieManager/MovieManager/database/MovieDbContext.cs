using Microsoft.EntityFrameworkCore;
using MovieManager.database.models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MovieManager.database
{
    public class MovieDbContext : DbContext
    {
        public DbSet<Movie> movies { get; set; }
        public DbSet<WeeklyBoxOffice> weeklyBoxOffice { get; set; }
        public DbSet<DailyBoxOffice> dailyBoxOffice { get; set; }




        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            //String connectionString = @"Server=localhost;Database=test;Uid=root;Pwd=1234;";
            //String connectionString = @"Server=localhost;Database=rank2movie;Uid=root;Pwd=1234;";
            String connectionString = @"Server=3.34.17.63;Database=rank2movie;Uid=root;Pwd=1234;";
            optionsBuilder.UseMySql(connectionString, ServerVersion.AutoDetect(connectionString));
        }

        // 영화 코드리스트에서 중복인걸 다 삭제해준다.
        public List<String> removeExist(List<String> codeList)
        {
            List<Movie> dbMovie = movies.ToList();
            for (int i = 0; i < codeList.Count; i++)
            {
                if (dbMovie.Exists(m => m.movieCode == codeList[i]))
                {
                    codeList[i] = null;
                }
            }

            codeList.RemoveAll(s => s == null);
            return codeList;
        }


        // 모든 주간 랭킹을 삭제해준다.
        public void resetWeeklyRanking()
        {
            weeklyBoxOffice.RemoveRange(weeklyBoxOffice);
            SaveChanges();
        }

        public void resetDailyRanking()
        {
            dailyBoxOffice.RemoveRange(dailyBoxOffice);
            SaveChanges();
        }

    }
}
