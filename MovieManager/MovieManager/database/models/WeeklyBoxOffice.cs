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
        [Column("rank_no")]
        public int rankNo { get; set; }

    }
}
