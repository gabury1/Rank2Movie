using MovieManager.database;
using MovieManager.database.models;
using MovieManager.working;

namespace MovieManager
{
    public partial class MainForm : Form
    {
        public MainForm()
        {
            InitializeComponent();

            Console.WriteLine("시작되었습니다!");

            DataCollector c = new DataCollector();
            Console.WriteLine(c.getMovieDetail("asdf"));


        }
    }
}