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
            String connectionString = @"Server=localhost;Database=test;Uid=root;Pwd=1234;";
            //String connectionString = @"Server=localhost;Database=rank2movie;Uid=root;Pwd=1234;";
            optionsBuilder.UseMySql(connectionString, ServerVersion.AutoDetect(connectionString));
        }
    }
}
