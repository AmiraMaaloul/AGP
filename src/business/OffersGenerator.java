package business;

import java.util.ArrayList;
import java.util.List;

// Cette classe permet de générer des offres à partir d'hotels et sites candidats (répondant aux critères de recherche)

public class OffersGenerator {

	private List<Hotel> candadidateHotels;

	private List<Site> candidateSites;
	
	private List<Offer> offers;

	public OffersGenerator(List<Hotel> candidateHotels, List<Site> candidateSites) {
		this.candadidateHotels = candidateHotels;
		this.candidateSites = candidateSites;
		
		offers = new ArrayList<Offer>();
	}

	public void generateOffers() {

	}
	
	public List<Offer> getOffers() {
		return offers;
	}

}
