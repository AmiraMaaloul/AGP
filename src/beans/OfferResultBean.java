package beans;

public class OfferResultBean {

	private String nameHotel;
	private int rating;
	private int priceHotel;
	private String nbExcursion;

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getNbExcursion() {
		return nbExcursion;
	}

	public void setNbExcursion(String nbExcursion) {
		this.nbExcursion = nbExcursion;
	}

	public int getPriceHotel() {
		return priceHotel;
	}

	public void setPriceHotel(int priceHotel) {
		this.priceHotel = priceHotel;
	}

	public String getNameHotel() {
		return nameHotel;
	}

	public void setNameHotel(String nameHotel) {
		this.nameHotel = nameHotel;
	}

}
