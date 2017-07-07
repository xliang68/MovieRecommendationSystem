
/**
 * Write a description of DirectorsFilter here.
 * 
 * @Justine Liang
 */
public class DirectorsFilter implements Filter {
    private String myDirectors;
    
    public DirectorsFilter(String directors) {
        myDirectors = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        String movieDirectors = MovieDatabase.getDirector(id);
        String testDirectors = new String(myDirectors);
        while (testDirectors.indexOf(",") != -1) {
            String director = testDirectors.substring(testDirectors.lastIndexOf(",") + 1);
            if (movieDirectors.indexOf(director) != -1) {
                return true;
            }
            testDirectors = testDirectors.substring(0, testDirectors.lastIndexOf(","));
        }
        if (movieDirectors.indexOf(testDirectors) != -1) {
            return true;
        }
        return false;
    }
}

