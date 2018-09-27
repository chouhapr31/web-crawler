package co.uk.prudential.demo;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CrawlerTest3 {

	Set<String> setOfUniqueLinks = new HashSet<String>();
	Set<String> setOfExternalUniqueLinks = new HashSet<String>();
	Crawler c = new Crawler();
	SiteMapper s = new SiteMapper();

	public void crawl(String url) {
		System.out.println("URL - " + url);
		HashMap<String, Map<String, Set<String>>> map = c.getLinksDetail(url);
		Collection<Map<String, Set<String>>> values = map.values();
		for (Map<String, Set<String>> set : values) {
			Set<String> internalLinks = set.get("internalLinks");
			Set<String> externalLinks = set.get("externalLinks");
			setOfUniqueLinks.addAll(internalLinks);
			setOfExternalUniqueLinks.addAll(externalLinks);
		}

		Iterator setOfUniqueLinksIterator = setOfUniqueLinks.iterator();
		while (setOfUniqueLinksIterator.hasNext()) {
			String links = (String) setOfUniqueLinksIterator.next();
			if (links != null && links.trim() != "")
				s.map(links);
			// crawl(links);
		}

	}

	// public static void main(String[] args) {
	// CrawlerTest1 cc = new CrawlerTest1();
	// cc.crawl("https://www.prudential.co.uk/");
	// }

	public static void main(String[] args) {
		String url = "https://www.prudential.co.uk/";
		SiteMapper s = new SiteMapper();
		Crawler c = new Crawler();
		HashMap<String, Map<String, Set<String>>> map = c.getLinksDetail(url);
		Set<String> setOfUniqueLinks = new HashSet<String>();
		Set<String> setOfExternalUniqueLinks = new HashSet<String>();
		s.map(url);
		Collection<Map<String, Set<String>>> values = map.values();

		for (Map<String, Set<String>> set : values) {
			Set<String> internalLinks = set.get("internalLinks");
			Set<String> externalLinks = set.get("externalLinks");
			setOfUniqueLinks.addAll(internalLinks);
			setOfExternalUniqueLinks.addAll(externalLinks);

//			Iterator setOfUniqueLinksIterator = setOfUniqueLinks.iterator();
//			while (setOfUniqueLinksIterator.hasNext()) {
//				String links = (String) setOfUniqueLinksIterator.next();
//				if (links != null && links.trim() != "")
//					s.mapToChild(url, links);
//			}
//			System.out.println("Unique completed");
			// Iterator i = internalLinks.iterator();
			// Set<String> setOfUniqueLinks1 = new HashSet<String>();
			// while (i.hasNext()) {
			// String link = (String) i.next();
			// // System.out.println(link);
			// HashMap<String, Map<String, Set<String>>> mapInternal =
			// c.getLinksDetail(link);
			// Collection<Map<String, Set<String>>> valuesInternal = mapInternal.values();
			// for (Map<String, Set<String>> set1 : valuesInternal) {
			// Set<String> internalLinks1 = set1.get("internalLinks");
			// Set<String> externalLinks1 = set1.get("externalLinks");
			// setOfUniqueLinks1.addAll(internalLinks1);
			// // setOfExternalUniqueLinks.addAll(externalLinks1);
			// // setOfUniqueLinks.addAll(internalLinks1);
			// // System.out.println(setOfUniqueLinks1.size());
			//
			// }
			// Iterator setOfUniqueLinksinternalLinksIterator =
			// setOfUniqueLinks1.iterator();
			// while (setOfUniqueLinksinternalLinksIterator.hasNext()) {
			// String links = (String) setOfUniqueLinksinternalLinksIterator.next();
			// if (links != null && !links.trim().isEmpty())
			// s.mapToChild(url, links);
			// // s.map(links);
			// }
			//
			// }

//			System.out.println("setOfUniqueLinks1 " + setOfUniqueLinks1.size());
		}
		// Iterator setOfUniqueLinksIterator = setOfUniqueLinks.iterator();
		// while (setOfUniqueLinksIterator.hasNext()) {
		// String links = (String) setOfUniqueLinksIterator.next();
		// if (links != null && links.trim() != "")
		// s.map(links);
		// }
		s.transform();
		// setOfUniqueLinks.add(link);
		/*
		 * HashMap<String, Map<String, Set<String>>> mapInternal = c.getDetails(url);
		 * 
		 * Collection<Map<String, Set<String>>> valuesInternal = mapInternal.values();
		 * 
		 * for (Map<String, Set<String>> setInternal : valuesInternal) { Set<String>
		 * internalLinksInternal = setInternal.get("internalLinks"); Iterator iInternal
		 * = internalLinksInternal.iterator();
		 * 
		 * while (iInternal.hasNext()) { String linkssInternal = (String)
		 * iInternal.next(); // System.out.println(linkssInternal); //
		 * sitemapGenerator.addUrl(linkssInternal);
		 * setOfUniqueLinks.add(linkssInternal); }
		 * 
		 * Set<String> externalLinks = setInternal.get("externalLinks"); Iterator
		 * iexternalLinks = externalLinks.iterator(); //
		 * setOfUniqueLinks.addAll(externalLinks); while (iexternalLinks.hasNext()) {
		 * String linkssExternal = (String) iexternalLinks.next(); //
		 * System.out.println(linkssExternal); //
		 * sitemapGenerator.addUrl(linkssExternal); // setOfUniqueLinks.ad } }
		 * 
		 * }
		 * 
		 * Set<String> externalLinks = set.get("externalLinks"); Iterator iexternalLinks
		 * = externalLinks.iterator();
		 * 
		 * // setOfUniqueLinks.addAll(externalLinks);
		 * 
		 * while (iexternalLinks.hasNext()) { String linkssExternal = (String)
		 * iexternalLinks.next(); // System.out.println(linkssInternal); //
		 * sitemapGenerator.addUrl(linkssExternal);
		 * 
		 * }
		 */
		// s.transform();
	}
	// }
	// Iterator setOfUniqueLinksIterator = setOfUniqueLinks.iterator();
	// while (setOfUniqueLinksIterator.hasNext()) {
	// String links = (String) setOfUniqueLinksIterator.next();
	// s.map(links);
	//
	// }

	// s.map("https://bt1.com");
	// s.map("https://bt2.com");
	// s.map("https://bt3.com");
	// s.map("https://bt4.com");
	// s.map("https://bt5.com");
	// s.mapToChild("https://bt.com", "https://abc.com");

}
