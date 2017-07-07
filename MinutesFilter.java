
/**
 * Write a description of MinutesFilter here.
 * 
 * @Justine Liang
 */
public class MinutesFilter implements Filter {
    private int myMinMinutes;
    private int myMaxMinutes;
    
    public MinutesFilter(int minMinutes, int maxMinutes) {
        myMinMinutes = minMinutes;
        myMaxMinutes = maxMinutes;
    }
    
    @Override
    public boolean satisfies(String id) {
        int movieMinutes = MovieDatabase.getMinutes(id);
        return ((myMinMinutes <= movieMinutes) && (movieMinutes <= myMaxMinutes));
    }

}