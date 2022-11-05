using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MovieManager.database.models
{
    [Table("weekly_box_office")]
    public class WeeklyBoxOffice
    {
        [Key]
        [Column("rank_no")] // 오늘의 순위
        public int rankNo { get; set; }

        [Column("new_or_old")] // 차트에 새로 진입한 영화인지 (New, Old)
        public String newOrOld { get; set; }

        [Column("rank_inten")] // 저번과의 순위 변동
        public int rankInten { get; set; }

        [Column("audi_cnt")] // 해당 일 관객 수
        public int audiCnt { get; set; }

        [Column("open_date")] // 개봉일
        public String openDate { get; set; }

        [ForeignKey("movie")]
        [Column("movie_code")]
        public String movieCode { get; set; }


        public Movie movie { get; set; }

    }
}
