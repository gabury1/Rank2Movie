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

            Console.WriteLine("���۵Ǿ����ϴ�!");

            DataCollector c = new DataCollector();
            Console.WriteLine(c.getMovieDetail("asdf"));


        }
    }
}