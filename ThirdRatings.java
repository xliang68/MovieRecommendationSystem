
/**
 * Write a description of ThirdRatings here.
 * 
 * @Justine Liang
 * @7/6/2017
 */

import java.util.*;

public class ThirdRatings {
    //private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
    	FirstRatings fr = new FirstRatings();
    	myRaters = fr.loadRaters(ratingsfile);
    }

    public int getRaterSize() {
    	return myRaters.size();
    }

    private double getAverageByID(String id, int minimalRaters) {
    	double totalRating = 0;
    	int numRating = 0;
		for (Rater rater: myRaters) {
			if (rater.hasRating(id)) {
				numRating = numRating + 1;
				totalRating = totalRating + rater.getRating(id);
			}
		}
		double avgRating = 0.0;
		if (numRating >= minimalRaters) {
			avgRating = totalRating/numRating;
		}
		return avgRating;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
    	ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
    	ArrayList<Rating> myRatings = new ArrayList<Rating>();
    	for (String movie: myMovies) {
    		double avgRating = getAverageByID(movie, minimalRaters);
    		if (avgRating != 0.0) {
    			Rating myRating = new Rating(movie, avgRating);
    			myRatings.add(myRating);
    		}
    	}
    	return myRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
    	ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);
    	ArrayList<Rating> myRatings = new ArrayList<Rating>();
    	for (String movie: myMovies) {
    		double avgRating = getAverageByID(movie, minimalRaters);
    		if (avgRating != 0.0) {
    			Rating myRating = new Rating(movie, avgRating);
    			myRatings.add(myRating);
    		}
    	}
    	return myRatings;
    }   
}


















