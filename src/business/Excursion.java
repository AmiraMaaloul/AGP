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

	public int getNbSites() {
		return sites.size();
	}

	public int getPrice() {
		int price = 0;
		for (Site s : sites) {
			price += Integer.valueOf(s.getSitePrice());
		}

		return price;
	}

	public List<Site> getSites() {
		return sites;
	}

	public boolean intersects(Excursion e) {
		for (Site site : e.sites) {
			if (sites.contains(site)) {
				return true;
			}
		}

		return false;
	}

}
