package co.uk.prudential.demo;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	// links is to make sure we maintain the history
	private static HashSet<String> links = new HashSet<String>();
	// internalLinksUnique is to keep the unique internal links
	private static Map<String, Set<String>> internalLinksUnique = new HashMap<>();
	// externalLinksUnique is to keep the unique external links
	private static Set<String> externalLinksUnique = new HashSet<String>();
	// mapSetOfImages is to keep the unique static contents
	private static Map<String, Set<String>> mapSetOfImages = new HashMap<>();
	private static String parentURL = "";

	public void search(String url, String path) {
		parentURL = url;
		SiteMapper s = new SiteMapper(path);
		HashMap<String, Map<String, Set<String>>> map = getLinksDetail(url);
		s.map(url);
		Collection<Map<String, Set<String>>> values = map.values();

		System.out.println("Searching and Iterating links start");

		/*
		 * Iterating child links
		 */

		for (Map<String, Set<String>> set : values) {
			Set<String> internalLinks = set.get("internalLinks");

			Iterator<String> setOfUniqueLinksIterator = internalLinks.iterator();
			while (setOfUniqueLinksIterator.hasNext()) {
				String links = (String) setOfUniqueLinksIterator.next();
				if (links != null
						&& links.trim() != "" /* && !links.equalsIgnoreCase("https://www.prudential.co.uk/") */)
					getLinksDetail(links);
			}
		}
		System.out.println(" Iterating Internal Links ");

		/*
		 * Internal links mapping
		 */

		Collection<Set<String>> col = internalLinksUnique.values();
		for (Map.Entry<String, Set<String>> entry : internalLinksUnique.entrySet()) {
			String key = entry.getKey();
			Set<String> setOfValues = entry.getValue();
			Iterator<String> setOfUniqueLinksIterator = setOfValues.iterator();
			while (setOfUniqueLinksIterator.hasNext()) {
				String links = (String) setOfUniqueLinksIterator.next();
				// if links end with .pdf then its a static content else its a links
				if (links.endsWith(".pdf")) {
					s.staticMap(key, links, parentURL);
				} else {
					s.mapToChild(parentURL, links);
				}
			}
		}

		System.out.println("Iterating External links");

		/*
		 * Iterating and mapping External links
		 */

		Iterator<String> externalSetOfUniqueLinksIterator = externalLinksUnique.iterator();
		while (externalSetOfUniqueLinksIterator.hasNext()) {
			String links = (String) externalSetOfUniqueLinksIterator.next();
			s.eMap(links);
		}

		System.out.println("Static content mapping start");
		/*
		 * Iterating images and mapping it
		 */
		for (Map.Entry<String, Set<String>> entry : mapSetOfImages.entrySet()) {
			String key = entry.getKey();
			Set<String> setOfValues = entry.getValue();

			Iterator<String> valuesIterator = setOfValues.iterator();

			while (valuesIterator.hasNext()) {
				String image = (String) valuesIterator.next();
				s.staticMap(key, image, parentURL);
			}
		}
		/*
		 * transforming the logical xml to actual xml file
		 */
		s.transform();
		// return internalLinksUnique;
	}

	public HashMap<String, Map<String, Set<String>>> getLinksDetail(String url) {
		HashMap<String, Map<String, Set<String>>> linkMap = new HashMap<>();
		Set<String> internalLinks = new HashSet<String>();
		Set<String> externalLinks = new HashSet<String>();
		Map<String, Set<String>> listOfLinks = new HashMap<>();
		Set<String> setOfImages = new HashSet<String>();

		if (url != null && url.trim() != "" && !links.contains(url)) {
			links.add(url);
			Document doc;
			try {
				doc = Jsoup.connect(url).get();
				Elements newsHeadlines = doc.select("a");
				Elements img = doc.getElementsByTag("img");
				for (Element headline : newsHeadlines) {

					String link = headline.absUrl("href");
					if (link.contains(parentURL)) {
						internalLinks.add(headline.absUrl("href"));
					} else {
						externalLinks.add(headline.absUrl("href"));
					}

				}
				for (Element el : img) {
					String src = el.absUrl("src");
					setOfImages.add(src);

				}
				listOfLinks.put("internalLinks", internalLinks);
				listOfLinks.put("externalLinks", externalLinks);
				linkMap.put(url, listOfLinks);
			} catch (IOException e) {
				// System.out.println("catch url " + url);
				getLinksDetail(url);
				// System.out.println("End catch url " + url);
			}
			mapSetOfImages.put(url, setOfImages);
			internalLinksUnique.put(url, internalLinks);
			externalLinksUnique.addAll(externalLinks);
		}

		return linkMap;
	}
}
