package co.uk.prudential.demo;

import java.io.File;
import java.util.Scanner;

public class CrawlerTest {

	private static String finalPath = "";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter URL");
		String url = sc.next();
		System.out.println(
				"Please give the path where you want to save the sitemap, Only give valid directory If you want to go with default which is C://Windows/Temp/,  please enter YES");
		String path = sc.next();
		finalPath = path;
		if (isValidDirectory(path, sc)) {
			Crawler c = new Crawler();
			c.search(url, finalPath.concat("sitemap.xml"));
		}

	}

	public static boolean isValidDirectory(String path, Scanner sc) {
		if ("YES".equalsIgnoreCase(path)) {
			finalPath = "C://Windows//Temp//";
			return true;
		}
		File f = new File(path);
		if (f.isDirectory()) {
			return true;
		} else {
			System.out.println("Please re-enter the valid directory ");
			String rePath = sc.next();
			finalPath = rePath;
			isValidDirectory(rePath, sc);
		}
		return true;
	}

}