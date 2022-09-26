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
        [Key]
        [Column("movie_code")]
        public string movieCode { get; set; }
    }
}
