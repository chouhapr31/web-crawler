package co.uk.prudential.demo;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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

import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;

public class CrawlerTest {

	private String baseUrl;

	public static void main(String[] args) {
		String url = "https://www.prudential.co.uk/";
		WebSitemapGenerator sitemapGenerator = null;
		CrawlerTest c = new CrawlerTest();
		try {

			HashMap<String, Map<String, Set<String>>> map = c.getDetails(url);
			Set<String> setOfUniqueLinks = new HashSet<String>();

			Collection<Map<String, Set<String>>> values = map.values();
			sitemapGenerator = WebSitemapGenerator
					.builder("https://www.prudential.co.uk/", new File("C:\\Windows\\Temp")).gzip(true)
					.allowMultipleSitemaps(true).build();
			
			for (Map<String, Set<String>> set : values) {
				Set<String> internalLinks = set.get("internalLinks");
				// setOfUniqueLinks.addAll(internalLinks);
				Iterator i = internalLinks.iterator();

				while (i.hasNext()) {
					String linkss = (String) i.next();
					// System.out.println(linkss);
					// sitemapGenerator.addUrl(linkss);
					setOfUniqueLinks.add(linkss);
					HashMap<String, Map<String, Set<String>>> mapInternal = c.getDetails(url);

					Collection<Map<String, Set<String>>> valuesInternal = mapInternal.values();

					for (Map<String, Set<String>> setInternal : valuesInternal) {
						Set<String> internalLinksInternal = setInternal.get("internalLinks");
						Iterator iInternal = internalLinksInternal.iterator();

						while (iInternal.hasNext()) {
							String linkssInternal = (String) iInternal.next();
							// System.out.println(linkssInternal);
							// sitemapGenerator.addUrl(linkssInternal);
							setOfUniqueLinks.add(linkssInternal);
						}

						Set<String> externalLinks = setInternal.get("externalLinks");
						Iterator iexternalLinks = externalLinks.iterator();
//						setOfUniqueLinks.addAll(externalLinks);
						while (iexternalLinks.hasNext()) {
							String linkssExternal = (String) iexternalLinks.next();
							// System.out.println(linkssExternal);
							// sitemapGenerator.addUrl(linkssExternal);
							// setOfUniqueLinks.ad
						}
					}

				}

				Set<String> externalLinks = set.get("externalLinks");
				Iterator iexternalLinks = externalLinks.iterator();

//				setOfUniqueLinks.addAll(externalLinks);

				while (iexternalLinks.hasNext()) {
					String linkssExternal = (String) iexternalLinks.next();
					// System.out.println(linkssInternal);
					// sitemapGenerator.addUrl(linkssExternal);

				}

				Iterator setOfUniqueLinksIterator = setOfUniqueLinks.iterator();
				while (setOfUniqueLinksIterator.hasNext()) {
					String link = (String) setOfUniqueLinksIterator.next();
					System.out.println(link);
					sitemapGenerator.addUrl(link);

				}

				sitemapGenerator.write();
			}
		} catch (MalformedURLException e) {
			// sitemapGenerator.write();
			e.printStackTrace();
		}
	}

	private String setBaseUrl(URL sitemapUrl) {
		String path = sitemapUrl.getPath();
		int lastPathDelimPos = path.lastIndexOf('/');
		if (lastPathDelimPos < 0) {
			path = "/";
		} else {
			path = path.substring(0, lastPathDelimPos + 1);
		}
		try {

			baseUrl = new URL(sitemapUrl.getProtocol(), sitemapUrl.getHost(), sitemapUrl.getPort(), path).toString();
		} catch (MalformedURLException e) {
			baseUrl = sitemapUrl.toString();
		}
		System.out.println(baseUrl);
		return baseUrl;
	}

	private static HashSet<String> links = new HashSet<String>();
	private HashMap<String, Map<String, Set<String>>> linkMap = new HashMap<>();

	public HashMap<String, Map<String, Set<String>>> getDetails(String url) {
		Set<String> internalLinks = new HashSet<String>();
		Set<String> externalLinks = new HashSet<String>();
		Map<String, Set<String>> listOfLinks = new HashMap<>();

		if (url != null && url.trim() != "" && !links.contains(url)) {
			links.add(url);
			links.forEach((e) -> {
				System.out.println("links iterator " + e);
			});
			Document doc;
			try {
				doc = Jsoup.connect(url).get();

				Elements newsHeadlines = doc.select("a");
				for (Element headline : newsHeadlines) {

					String link = headline.absUrl("href");
					if (link.contains("https://www.prudential.co.uk/")) {

						internalLinks.add(headline.absUrl("href"));

					} else {
						externalLinks.add(headline.absUrl("href"));
					}

				}

				listOfLinks.put("internalLinks", internalLinks);
				listOfLinks.put("externalLinks", externalLinks);
				linkMap.put(url, listOfLinks);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return linkMap;
	}

}
