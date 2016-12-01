package refactoringtest;

public class Rental {
	private Movie  mMovie;
	private int mDayRented;
	public Rental(Movie mMovie, int mDayRented) {
		super();
		this.mMovie = mMovie;
		this.mDayRented = mDayRented;
	}
	public Movie getmMovie() {
		return mMovie;
	}
	public int getmDayRented() {
		return mDayRented;
	}
	
	

}
