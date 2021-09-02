package com.banger.backend.Scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
public class WebScrapingImplementation {


    public void webScrapper() throws IOException {
        Document doc = Jsoup.connect("https://www.malkey.lk/rates/self-drive-rates.html").get();

        Elements header =doc.select("tr");
        System.out.println(header);
        for(int i=0;i<=4;i++){

        }
    }
}
