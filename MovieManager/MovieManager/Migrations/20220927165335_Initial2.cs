using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace MovieManager.Migrations
{
    public partial class Initial2 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.UpdateData(
                table: "movie",
                keyColumn: "title_kor",
                keyValue: null,
                column: "title_kor",
                value: "");

            migrationBuilder.AlterColumn<string>(
                name: "title_kor",
                table: "movie",
                type: "longtext",
                nullable: false,
                oldClrType: typeof(string),
                oldType: "longtext",
                oldNullable: true)
                .Annotation("MySql:CharSet", "utf8mb4")
                .OldAnnotation("MySql:CharSet", "utf8mb4");

            migrationBuilder.AddColumn<int>(
                name: "views",
                table: "movie",
                type: "int",
                nullable: false,
                defaultValue: 0);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "views",
                table: "movie");

            migrationBuilder.AlterColumn<string>(
                name: "title_kor",
                table: "movie",
                type: "longtext",
                nullable: true,
                oldClrType: typeof(string),
                oldType: "longtext")
                .Annotation("MySql:CharSet", "utf8mb4")
                .OldAnnotation("MySql:CharSet", "utf8mb4");
        }
    }
}
