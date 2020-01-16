package business;

import java.util.ArrayList;
import java.util.List;

// Cette classe permet de générer des offres à partir d'hotels et sites candidats (répondant aux critères de recherche)

public class OffersGenerator {

	private List<Hotel> candidateHotels;

	private List<Site> candidateSites;
	
	private List<Offer> offers;
	
	private int minPrice;
	
	private int maxPrice;
	
	private int nbDays;
	
	private int nbExcursions;

	public OffersGenerator(List<Hotel> candidateHotels, List<Site> candidateSites, int minPrice, int maxPrice, int nbDays, int nbExcursions) {
		this.candidateHotels = candidateHotels;
		this.candidateSites = candidateSites;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.nbDays = nbDays;
		this.nbExcursions = nbExcursions;
		
		offers = new ArrayList<Offer>();
	}

	public void generateOffers() {
		Offer offer = new Offer();
		
		for (Hotel hotel : candidateHotels) {
			offer = generateOffer(hotel);
		}
		
	}
	
	public Offer generateOffer(Hotel hotel) {
		return null;
	}
	
	public List<Offer> getOffers() {
		return offers;
	}

}
