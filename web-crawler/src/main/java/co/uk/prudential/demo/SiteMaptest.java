package co.uk.prudential.demo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import com.redfin.sitemapgenerator.WebSitemapGenerator;

public class SiteMaptest {

	private String baseUrl;

	public static void main(String[] args) throws Exception {
		// try {
		// WebSitemapGenerator sitemapGenerator = WebSitemapGenerator
		// .builder("https://github.com/", new
		// File("E:\\Prashant\\Documents_per\\crawler&sitemap")).gzip(true)
		// .build();
		// sitemapGenerator.addUrl("https://github.com/Codingpedia/podcastpedia");
		// sitemapGenerator.write();
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// }

		SiteMaptest t = new SiteMaptest();
		t.setBaseUrl(new URL("http://example.com/abc/sitemap.xml"));

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
			System.out.println(
					sitemapUrl.getProtocol() + " " + sitemapUrl.getHost() + " " + sitemapUrl.getPort() + " " + path);
			baseUrl = new URL(sitemapUrl.getProtocol(), sitemapUrl.getHost(), sitemapUrl.getPort(), path).toString();
		} catch (MalformedURLException e) {
			baseUrl = sitemapUrl.toString();
		}
		return baseUrl;
	}
}
