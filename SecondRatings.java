
/**
 * Write a description of SecondRatings here.
 * 
 * @Justine Liang
 * @7/6/2017
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
    	FirstRatings fr = new FirstRatings();
    	myMovies = fr.loadMovies(moviefile);
    	myRaters = fr.loadRaters(ratingsfile);
    }

    public int getMovieSize() {
    	return myMovies.size();
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
    	ArrayList<Rating> myRatings = new ArrayList<Rating>();
    	for (Movie movie: myMovies) {
    		double avgRating = getAverageByID(movie.getID(), minimalRaters);
    		if (avgRating != 0.0) {
    			Rating myRating = new Rating(movie.getID(), avgRating);
    			myRatings.add(myRating);
    		}
    	}
    	return myRatings;
    }

    public String getTitle(String id) {
    	for (Movie movie: myMovies) {
    		if (movie.getID().equals(id)) {
    			return movie.getTitle();
    		}
    	}
    	String error_message = "Movie with ID " + id + " was not found.";
    	return error_message;
    }

    public String getID(String title) {
    	for (Movie movie: myMovies) {
    		if (movie.getTitle().equals(title)) {
    			return movie.getID();
    		}
    	}
    	String error_message = "NO SUCH TITLE.";
    	return error_message;
    }
}