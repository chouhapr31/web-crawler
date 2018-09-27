# web-crawler

# How to build and run your solution

1. mvn clean install
2. mvn compile
3. mvn exec:java -Dexec.mainClass=co.uk.prudential.demo.CrawlerTest
4. It will ask for URL so type - https://www.prudential.co.uk
5. It will ask for path where we want to save sitemap xml , Enter YES for default directory which is C://Windows//Temp//, If you want to give another directory, mentioned there then Enter.

# Explanation of what could be done with more time

Currently its taking time to process as we are also scanning static pages, If I would have more time, will like to implement Threading to improve time performance.

# Reasoning and describe any trade offs

Earlier I was using  SitemapGen4J library to create a sitemap but this library is not allowing to add external links in sitemap due to different base url. So I have created my own sitemapper class.
