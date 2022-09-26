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
        public DbSet<Movie> Movies { get; set; }
        public DbSet<WeeklyBoxOffice> WeeklyBoxOffice { get; set; }
        public DbSet<DailyBoxOffice> DailyBoxOffice { get; set; }



        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            String connectionString = @"Server=localhost;Database=test;Uid=root;Pwd=1234;";
            optionsBuilder.UseMySql(connectionString, ServerVersion.AutoDetect(connectionString));
        }
    }
}
