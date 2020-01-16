package business;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class OffersGeneratorTest {

	@Test
	public void testGeneration() {

		Hotel h1 = TestDataFactory.getHotel("H1", 10);
		Hotel h2 = TestDataFactory.getHotel("H2", 15);
		Site s1 = TestDataFactory.getSite("S1", 100);
		Site s2 = TestDataFactory.getSite("S2", 150);
		Site s3 = TestDataFactory.getSite("S3", 150);
		Site s4 = TestDataFactory.getSite("S4", 150);
		Site s5 = TestDataFactory.getSite("S5", 150);
		Site s6 = TestDataFactory.getSite("S6", 150);
		Site s7 = TestDataFactory.getSite("S7", 150);

		List<Hotel> hotels = Arrays.asList(h1, h2);
		List<Site> sites = Arrays.asList(s1, s2, s3, s4, s5, s6, s7);

		OffersGenerator generator = new OffersGenerator(hotels, sites, 0, 10000, 10, 1, 3);

		List<Offer> offers = generator.generateOffers();
		
		int i=0;
		for (Offer offer : offers) {
			System.out.println("Offre " + (++i) + " Hotel: " + offer.getHotel().getHotelName());
			for (Excursion excursion : offer.getExcursions()) {
				System.out.println("Excursion: ");
				for (Site site : excursion.getSites()) {
					System.out.print(site.getSiteName()+ " -> ");
				}
				System.out.println("");
				System.out.println("");
				
			}
		}

		assertEquals(4, offers.size());

	}
}
