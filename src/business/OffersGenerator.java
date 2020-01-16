package business;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

// Cette classe permet de générer des offres à partir d'hotels et sites candidats (répondant aux critères de recherche)

public class OffersGenerator {

	private List<Hotel> candidateHotels;

	private List<Site> candidateSites;

	private int minPrice;

	private int maxPrice;

	private int nbDays;

	private int nbExcursions;

	private int nbSites;

	public OffersGenerator(List<Hotel> candidateHotels, List<Site> candidateSites, int minPrice, int maxPrice,
			int nbDays, int nbExcursions, int nbSites) {
		this.candidateHotels = candidateHotels;
		this.candidateSites = candidateSites;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.nbDays = nbDays;
		this.nbExcursions = nbExcursions;
		this.nbSites = nbSites;

	}

	public List<Offer> generateOffers() {
		List<Offer> offers = new ArrayList<Offer>();

		for (Hotel hotel : candidateHotels) {
			offers.addAll(generateOffers(hotel));
		}

		return offers;
	}

	/**
	 * @param hotel
	 * @return
	 */
	private List<Offer> generateOffers(Hotel hotel) {

		List<Offer> offersForHotel = new ArrayList<Offer>();

		List<Excursion> excursions = generateAllExcursions(candidateSites, nbSites);

		boolean stop = false;

		while (!excursions.isEmpty() && !stop) {

			Offer offer = new Offer();
			offer.setHotel(hotel);

			int totalPrice = Integer.valueOf(hotel.getHotelPrice()) * nbDays;

			int i = 0;
			while (offer.getNbExcursions() != nbExcursions && i < excursions.size()) {
				Excursion selectedExcursion = excursions.get(i);
				totalPrice += selectedExcursion.getPrice();

				if (totalPrice < maxPrice) {
					offer.addExcursion(selectedExcursion);
					excursions.remove(selectedExcursion);

					removeSelectedExcursion(excursions, selectedExcursion);

				} else {
					totalPrice -= selectedExcursion.getPrice();
					i++;
				}
			}
			if (offer.getNbExcursions() == nbExcursions) {
				offersForHotel.add(offer);
			} else {
				stop = true;
			}
		}

		return offersForHotel;
	}

	private void removeSelectedExcursion(List<Excursion> filteredExcursions, Excursion selectedExcursion) {

		ListIterator<Excursion> iterator = filteredExcursions.listIterator();
		while (iterator.hasNext()) {
			Excursion excursion = iterator.next();

			if (excursion.intersects(selectedExcursion)) {
				iterator.remove();
			}
		}

	}

	private List<Excursion> generateAllExcursions(List<Site> availableSites, int nbSites) {

		if (availableSites.isEmpty() || nbSites == 0) {
			List<Excursion> excursions = new ArrayList<Excursion>();
			excursions.add(new Excursion());
			return excursions;
		}

		List<Excursion> excursions = new ArrayList<Excursion>();

		for (Site site : availableSites) {

			List<Site> remainingSites = new ArrayList<Site>(availableSites);
			remainingSites.remove(site);

			List<Excursion> prevResult = generateAllExcursions(remainingSites, nbSites - 1);

			for (Excursion excursion : prevResult) {
				excursion.addSite(site);
				excursions.add(excursion);
			}
		}

		return excursions;
	}
}
