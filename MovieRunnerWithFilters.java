
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @Justine Liang
 */

import java.util.*;

public class MovieRunnerWithFilters {

	public void printAverageRatings() {
		//String ratingsfile = "data/ratings_short.csv";
		String ratingsfile = "data/ratings.csv";
		
		ThirdRatings tr = new ThirdRatings(ratingsfile);
		System.out.println("There are " + tr.getRaterSize() + " raters in file " + ratingsfile.substring(5));
		
		//String moviefile = "ratedmovies_short.csv";
		String moviefile = "ratedmoviesfull.csv";
		MovieDatabase.initialize(moviefile);
		System.out.println("There are " + MovieDatabase.size() + " movies in file " + moviefile.substring(5));
		System.out.println("----------------------------------------");

		int minimalRaters = 35;
		ArrayList<Rating> ratings = tr.getAverageRatings(minimalRaters);
		System.out.println(ratings.size() + " movies with ratings are returned.");
		Collections.sort(ratings, new Comparator<Rating>(){
			@Override
			public int compare(Rating r1, Rating r2) {
				return Double.compare(r1.getValue(), r2.getValue());
			}
		});
		for (Rating rating: ratings) {
			System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
		}
	}

	public void printAverageRatingsByYear() {
		//String ratingsfile = "data/ratings_short.csv";
		String ratingsfile = "data/ratings.csv";
		
		ThirdRatings tr = new ThirdRatings(ratingsfile);
		System.out.println("There are " + tr.getRaterSize() + " raters in file " + ratingsfile.substring(5));
		
		//String moviefile = "ratedmovies_short.csv";
		String moviefile = "ratedmoviesfull.csv";
		MovieDatabase.initialize(moviefile);
		System.out.println("There are " + MovieDatabase.size() + " movies in file " + moviefile.substring(5));
		System.out.println("----------------------------------------");

		int minimalRaters = 20;
		int year = 2000;
		Filter filterCriteria = new YearAfterFilter(year);
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
		System.out.println(ratings.size() + " movies with ratings are returned.");
		Collections.sort(ratings, new Comparator<Rating>(){
			@Override
			public int compare(Rating r1, Rating r2) {
				return Double.compare(r1.getValue(), r2.getValue());
			}
		});
		for (Rating rating: ratings) {
			System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) 
				+ " " + MovieDatabase.getTitle(rating.getItem()));
		}
	}

	public void printAverageRatingsByGenre() {
		//String ratingsfile = "data/ratings_short.csv";
		String ratingsfile = "data/ratings.csv";
		
		ThirdRatings tr = new ThirdRatings(ratingsfile);
		System.out.println("There are " + tr.getRaterSize() + " raters in file " + ratingsfile.substring(5));
		
		//String moviefile = "ratedmovies_short.csv";
		String moviefile = "ratedmoviesfull.csv";
		MovieDatabase.initialize(moviefile);
		System.out.println("There are " + MovieDatabase.size() + " movies in file " + moviefile.substring(5));
		System.out.println("----------------------------------------");

		int minimalRaters = 20;
		String genre = "Comedy";
		Filter filterCriteria = new GenreFilter(genre);
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
		System.out.println(ratings.size() + " movies with ratings are returned.");
		Collections.sort(ratings, new Comparator<Rating>(){
			@Override
			public int compare(Rating r1, Rating r2) {
				return Double.compare(r1.getValue(), r2.getValue());
			}
		});
		for (Rating rating: ratings) {
			System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
			System.out.print("    ");
			System.out.println(MovieDatabase.getGenres(rating.getItem()));
		}
	}

	public void printAverageRatingsByMinutes() {
		//String ratingsfile = "data/ratings_short.csv";
		String ratingsfile = "data/ratings.csv";
		
		ThirdRatings tr = new ThirdRatings(ratingsfile);
		System.out.println("There are " + tr.getRaterSize() + " raters in file " + ratingsfile.substring(5));
		
		//String moviefile = "ratedmovies_short.csv";
		String moviefile = "ratedmoviesfull.csv";
		MovieDatabase.initialize(moviefile);
		System.out.println("There are " + MovieDatabase.size() + " movies in file " + moviefile.substring(5));
		System.out.println("----------------------------------------");

		int minimalRaters = 5;
		int minMinutes = 105;
		int maxMinutes = 135;
		Filter filterCriteria = new MinutesFilter(minMinutes, maxMinutes);
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
		System.out.println(ratings.size() + " movies with ratings are returned.");
		Collections.sort(ratings, new Comparator<Rating>(){
			@Override
			public int compare(Rating r1, Rating r2) {
				return Double.compare(r1.getValue(), r2.getValue());
			}
		});
		for (Rating rating: ratings) {
			System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) 
				+ " " + MovieDatabase.getTitle(rating.getItem()));
		}
	}

	public void printAverageRatingsByDirectors() {
		//String ratingsfile = "data/ratings_short.csv";
		String ratingsfile = "data/ratings.csv";
		
		ThirdRatings tr = new ThirdRatings(ratingsfile);
		System.out.println("There are " + tr.getRaterSize() + " raters in file " + ratingsfile.substring(5));
		
		//String moviefile = "ratedmovies_short.csv";
		String moviefile = "ratedmoviesfull.csv";
		MovieDatabase.initialize(moviefile);
		System.out.println("There are " + MovieDatabase.size() + " movies in file " + moviefile.substring(5));
		System.out.println("----------------------------------------");

		int minimalRaters = 4;
		String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
		Filter filterCriteria = new DirectorsFilter(directors);
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
		System.out.println(ratings.size() + " movies with ratings are returned.");
		Collections.sort(ratings, new Comparator<Rating>(){
			@Override
			public int compare(Rating r1, Rating r2) {
				return Double.compare(r1.getValue(), r2.getValue());
			}
		});
		for (Rating rating: ratings) {
			System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
			System.out.print("    ");
			System.out.println(MovieDatabase.getDirector(rating.getItem()));
		}
	}

	public void printAverageRatingsByYearAndGenre() {
		//String ratingsfile = "data/ratings_short.csv";
		String ratingsfile = "data/ratings.csv";
		
		ThirdRatings tr = new ThirdRatings(ratingsfile);
		System.out.println("There are " + tr.getRaterSize() + " raters in file " + ratingsfile.substring(5));
		
		//String moviefile = "ratedmovies_short.csv";
		String moviefile = "ratedmoviesfull.csv";
		MovieDatabase.initialize(moviefile);
		System.out.println("There are " + MovieDatabase.size() + " movies in file " + moviefile.substring(5));
		System.out.println("----------------------------------------");

		int minimalRaters = 8;

		int year = 1990;
		Filter filterCriteria1 = new YearAfterFilter(year);

		String genre = "Drama";
		Filter filterCriteria2 = new GenreFilter(genre);

		AllFilters filterCriteria = new AllFilters();
		filterCriteria.addFilter(filterCriteria1);
		filterCriteria.addFilter(filterCriteria2);

		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
		System.out.println(ratings.size() + " movies with ratings are returned.");
		Collections.sort(ratings, new Comparator<Rating>(){
			@Override
			public int compare(Rating r1, Rating r2) {
				return Double.compare(r1.getValue(), r2.getValue());
			}
		});
		for (Rating rating: ratings) {
			System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem())
				+ " " + MovieDatabase.getTitle(rating.getItem()));
			System.out.print("    ");
			System.out.println(MovieDatabase.getGenres(rating.getItem()));
		}
	}

	public void printAverageRatingsByDirectorsAndMinutes() {
		//String ratingsfile = "data/ratings_short.csv";
		String ratingsfile = "data/ratings.csv";
		
		ThirdRatings tr = new ThirdRatings(ratingsfile);
		System.out.println("There are " + tr.getRaterSize() + " raters in file " + ratingsfile.substring(5));
		
		//String moviefile = "ratedmovies_short.csv";
		String moviefile = "ratedmoviesfull.csv";
		MovieDatabase.initialize(moviefile);
		System.out.println("There are " + MovieDatabase.size() + " movies in file " + moviefile.substring(5));
		System.out.println("----------------------------------------");

		int minimalRaters = 3;

		String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
		Filter filterCriteria1 = new DirectorsFilter(directors);
		
		int minMinutes = 90;
		int maxMinutes = 180;
		Filter filterCriteria2 = new MinutesFilter(minMinutes, maxMinutes);

		AllFilters filterCriteria = new AllFilters();
		filterCriteria.addFilter(filterCriteria1);
		filterCriteria.addFilter(filterCriteria2);

		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
		System.out.println(ratings.size() + " movies with ratings are returned.");
		Collections.sort(ratings, new Comparator<Rating>(){
			@Override
			public int compare(Rating r1, Rating r2) {
				return Double.compare(r1.getValue(), r2.getValue());
			}
		});
		for (Rating rating: ratings) {
			System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) 
				+ " " + MovieDatabase.getTitle(rating.getItem()));
			System.out.print("    ");
			System.out.println(MovieDatabase.getDirector(rating.getItem()));
		}
	}

}
