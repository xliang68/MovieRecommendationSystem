
/**
 * Write a description of GenreFilter here.
 * 
 * @Justine Liang
 */
public class GenreFilter implements Filter {
    private String myGenre;
    
    public GenreFilter(String genre) {
        myGenre = genre;
    }
    
    @Override
    public boolean satisfies(String id) {
        if (MovieDatabase.getGenres(id).indexOf(myGenre) != -1) {
            return true;
        }
        else {
            return false;
        }
    }

}
