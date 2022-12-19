using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MovieManager.database.models
{
    [Table("movie")]
    public class Movie
    {
        // 영화 정보를 저장하는 엔티티


        // 기본키 (영화 코드)
        [Key]
        [Column("movie_code")]
        public string movieCode { get; set; }


        // 영화정보
        [Column("title_kor")] // 영화 제목
        public string titleKor { get; set; }
        [Column("title_en")] // 영화제목(영어)
        public string? titleEn { get; set; }


        [Column("genre")] // 영화 장르
        public string? genre { get; set; }
        
        [Column("actor")] // 영화 배우 목록 (쉼표로 구분된다)
        public string? actor { get; set; }

        [Column("show_time")] // 영화 상영 시간
        public int showTime { get; set; }

        [Column("watch_grade")] // 영화 관람 등급
        public string? watchGrade { get; set; }


        // 제작 정보
        [Column("company")] // 제작사명
        public string? companyName { get; set; }
        [Column("director")] // 감독
        public string? director { get; set; }
        [Column("product_status")] // 제작 상태
        public string? productStatus { get; set; }
        [Column("product_year")] // 제작 년도
        public string? productYear { get; set; }
        [Column("open_date")] // 개봉일
        public string? openDate { get; set; }
        [Column("nation")] // 제작 국가
        public string? nation { get; set; }



        // 이미지 URL
        [Column("image_url")]
        public string? imageUrl { get; set; }


        

        // 얘네 둘은 여기 관할이 아니다.
        // 조회수
        [Column("views")]
        public int views { get; set; }

        // 영화 평점
        [Column("avr_rating")]
        public double ? avr_rating { get; set; }


    }
}
