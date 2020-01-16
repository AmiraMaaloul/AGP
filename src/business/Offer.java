package business;

import java.util.ArrayList;
import java.util.List;

public class Offer {

	private Hotel hotel;

	private List<Excursion> excursions;

	public Offer() {
		excursions = new ArrayList<Excursion>();
	}

	public void addExcursion(Excursion excursion) {
		excursions.add(excursion);
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<Excursion> getExcursions() {
		return excursions;
	}

	public void setExcursions(List<Excursion> excursions) {
		this.excursions = excursions;
	}

}
