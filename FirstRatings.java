import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * @Justine Liang 
 */
public class FirstRatings {
    
    public ArrayList<Movie> loadMovies (String filename){
        FileResource fr = new FileResource(filename);
        ArrayList<Movie> movies = new ArrayList<Movie>();
        for (CSVRecord record: fr.getCSVParser()) {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genre = record.get("genre");
            String director = record.get("director");
            int theMinutes = Integer.parseInt(record.get("minutes"));
            String poster = record.get("poster");
            Movie movie = new Movie(id, title, year, genre, director, country, poster, theMinutes);
            movies.add(movie);
        }
        return movies;
    }
    
    public void testLoadMoviews () {
    	String filename = "data/ratedmovies_short.csv";
    	//String filename = "data/ratedmoviesfull.csv";
    	ArrayList<Movie> movies = loadMovies(filename);
    	System.out.println(filename.substring(5) + " contains " + movies.size() + " movies.");
    	int numComedy = 0;
    	int numLongerThan150 = 0;
    	HashMap<String, Integer> numDirector = new HashMap<String, Integer>();
    	for (Movie movie: movies) {
    		System.out.println(movie);
    		String genres = movie.getGenres();
    		if (genres.indexOf("Comedy") != -1) {
    			numComedy = numComedy + 1;
    		}
    		int minutes = movie.getMinutes();
    		if (minutes > 150) {
    			numLongerThan150 = numLongerThan150 + 1;
    		}
    		String directors = movie.getDirector();
    		while (directors.indexOf(", ") != -1) {
    			String director = directors.substring(0, directors.indexOf(", "));
    			if (numDirector.containsKey(director)) {
    				numDirector.put(director, numDirector.get(director)+1);
    			}
    			else {
    				numDirector.put(director, 1);
    			}
    			directors = directors.substring(directors.indexOf(", ")+2);
    		}
    		if (numDirector.containsKey(directors)) {
    			numDirector.put(directors, numDirector.get(directors)+1);
    		}
    		else {
    			numDirector.put(directors, 1);
    		}
    	}
    	System.out.println(filename.substring(5) + " contains " + numComedy + " movies of Comedy Genre.");
    	System.out.println(filename.substring(5) + " contains " + numLongerThan150 + " movies longer than 150 minutes.");

    	int numMaxMovies = Collections.max(numDirector.values());
    	Iterator it = numDirector.entrySet().iterator();
    	while (it.hasNext()) {
    		Map.Entry pair = (Map.Entry) it.next();
    		if (pair.getValue().equals(numMaxMovies)) {
    			System.out.println(filename.substring(5) + " has director "
    				+ pair.getKey() + " with the maximum number of movies " + pair.getValue());
    		}
    	}
    }
    
    public ArrayList<Rater> loadRaters (String filename){
    	FileResource fr = new FileResource(filename);
        ArrayList<Rater> raters = new ArrayList<Rater>();
        ArrayList<String> ids = new ArrayList<String>();
        for (CSVRecord record: fr.getCSVParser()) {
        	String id = record.get("rater_id");
        	String item = record.get("movie_id");
        	double rating = Double.parseDouble(record.get("rating"));
        	if (ids.contains(id)) {
        		for (Rater rater: raters) {
        			if (rater.getID().equals(id)) {
        				rater.addRating(item, rating);
        			}
        		}
        	}
        	else {
        		ids.add(id);
        		Rater rater = new EfficientRater(id);
        		raters.add(rater);
        		rater.addRating(item, rating);
        	}
        }
        return raters;
    }
    
    public void testLoadRaters () {
    	String filename = "data/ratings_short.csv";
    	//String filename = "data/ratings.csv";
    	ArrayList<Rater> raters = loadRaters(filename);
    	System.out.println(filename.substring(5) + " contains " + raters.size() + " raters.");
    	for (Rater rater: raters) {
    		System.out.println("Rater " + rater.getID() + " did " + rater.numRatings() + " ratings.");
    		for (String item: rater.getItemsRated()) {
    			System.out.print("  ");
    			System.out.println(item + " is rated at " + rater.getRating(item));
    		}
    	}
    	System.out.println("----------------------------------------");
    	HashMap<String, Integer> numRatingsPerRater = new HashMap<String, Integer>();
    	for (Rater rater: raters) {
    		numRatingsPerRater.put(rater.getID(), rater.numRatings());
    	}
    	String rater_id = "2";
    	//String rater_id = "193";
    	System.out.println("Rater " + rater_id + " did " + numRatingsPerRater.get(rater_id) + " ratings.");
    	System.out.println("----------------------------------------");
    	int numMaxRatings = Collections.max(numRatingsPerRater.values());
    	Iterator it = numRatingsPerRater.entrySet().iterator();
    	while (it.hasNext()) {
    		Map.Entry pair = (Map.Entry) it.next();
    		if (pair.getValue().equals(numMaxRatings)) {
    			System.out.println(pair.getKey() + " did the maximum number of ratings " + pair.getValue());
    		}
    	}
		System.out.println("----------------------------------------");
		HashMap<String, Integer> numRatingsPerMovie = new HashMap<String, Integer>();
		for (Rater rater: raters) {
    		for (String movie_id: rater.getItemsRated()) {
    			if (numRatingsPerMovie.containsKey(movie_id)) {
    				numRatingsPerMovie.put(movie_id, numRatingsPerMovie.get(movie_id)+1);
    			}
    			else {
    				numRatingsPerMovie.put(movie_id, 1);
    			}
    		}
    	}
    	String movie_id = "1798709";
    	System.out.println("Movie " + movie_id + " received " + numRatingsPerMovie.get(movie_id) + " ratings.");
    	System.out.println("----------------------------------------");
    	System.out.println("There are " + numRatingsPerMovie.size() + " movies rated in total.");
    	
    }

}
