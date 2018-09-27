package co.uk.prudential.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test3 {
	public static void main(String[] args) {

		String links = "https://www.prudential.co.uk/~/media/Files/P/Prudential-V2/reports/2017/Prudential-Plc-2017-Half-year-financial-report.pdf";

		if (links.endsWith(".pdf")) {
			System.out.println(links);
		}

	}
}
