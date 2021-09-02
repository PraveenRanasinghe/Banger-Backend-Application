package com.banger.backend.Scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
public class WebScrapingImplementation {


    public void webScrapper() throws IOException {
        Document doc = Jsoup.connect("https://www.malkey.lk/rates/self-drive-rates.html").get();

        Elements header =doc.getElementsByClass("darkgray text-center percent-40");
        System.out.println(header);

        Elements subHeader =doc.getElementsByClass("darkgray text-center percent-22");
        System.out.println(subHeader);

        Elements newsHeadlines = doc.getElementsByClass("text-left percent-40");
        Elements priceList =doc.getElementsByClass("text-center percent-22");
        for (Element headline : newsHeadlines) {
            System.out.println(headline);
        }

        for (Element headline : priceList) {
            System.out.println(headline);
        }
    }

}
