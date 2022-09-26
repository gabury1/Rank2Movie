using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MovieManager.database.models
{
    [Table("daily_box_office")]
    public class DailyBoxOffice
    {
        [Key]
        [Column("rank_no")]
        public int rankNo { get; set; }


    }
}
