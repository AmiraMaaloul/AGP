package business;

import java.util.ArrayList;
import java.util.List;

public class Excursion {

	private List<Site> sites;

	public Excursion() {
		sites = new ArrayList<Site>();
	}

	public void addSite(Site s) {
		sites.add(s);
	}

	public int getPrice() {
		int price = 0;
		for (Site s : sites) {
			price += Integer.valueOf(s.getSitePrice());
		}

		return price;
	}

}
