using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace MovieManager.Migrations
{
    public partial class initial1 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "movie_rating",
                table: "movie",
                newName: "avr_rating");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "avr_rating",
                table: "movie",
                newName: "movie_rating");
        }
    }
}
