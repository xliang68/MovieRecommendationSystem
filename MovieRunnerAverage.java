
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @Justine Liang
 * @7/6/2017
 */

import java.util.*;

public class MovieRunnerAverage {

	public void printAverageRatings() {
		//String moviefile = "data/ratedmovies_short.csv";
		String moviefile = "data/ratedmoviesfull.csv";
		//String ratingsfile = "data/ratings_short.csv";
		String ratingsfile = "data/ratings.csv";
		SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
		System.out.println("There are " + sr.getMovieSize() + " movies in file " + moviefile.substring(5));
		System.out.println("There are " + sr.getRaterSize() + " raters in file " + ratingsfile.substring(5));
		System.out.println("----------------------------------------");
		int minimalRaters = 12;
		ArrayList<Rating> ratings = sr.getAverageRatings(minimalRaters);
		Collections.sort(ratings, new Comparator<Rating>(){
			@Override
			public int compare(Rating r1, Rating r2) {
				return Double.compare(r1.getValue(), r2.getValue());
			}
		});
		for (Rating rating: ratings) {
			System.out.println(rating.getValue() + " " + sr.getTitle(rating.getItem()));
		}
	}

	public void getAverageRatingOneMovie() {
		String moviefile = "data/ratedmovies_short.csv";
		//String moviefile = "data/ratedmoviesfull.csv";
		String ratingsfile = "data/ratings_short.csv";
		//String ratingsfile = "data/ratings.csv";
		SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
		String title = "The Godfather";
		//String title = "The Maze Runner";
		//String title = "Moneyball";
		//String title = "Vacation";
		String movie_id = sr.getID(title);
		// notice that here we have no requirements on number of minimal raters, so we set it as 0
		int minimalRaters = 0;
		ArrayList<Rating> ratings = sr.getAverageRatings(minimalRaters);
		for (Rating rating: ratings) {
			if (rating.getItem().equals(movie_id)) {
				System.out.println("The average rating for the movie " + title + " would be " + rating.getValue());
			}
		}
	}

}
