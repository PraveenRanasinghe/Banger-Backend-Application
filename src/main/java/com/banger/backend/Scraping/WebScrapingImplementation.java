package com.banger.backend.Scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class WebScrapingImplementation {


    public void webScrapper() throws IOException {
        Document doc = Jsoup.connect("https://www.malkey.lk/rates/self-drive-rates.html").get();

        Elements header =doc.select("tr");

        List<Scrapper> scrapperList = new ArrayList<>();

        for(int i=0;i<header.size();i++){
            Elements td =doc.select("td");
               for(int j=0; j<td.size(); j++){

                   Scrapper scrapper = new Scrapper();

                   scrapper.setVehicleType(td.get(j).text());
                   scrapper.setRatePerMonth(td.get(j).text());
                   scrapper.setRatePerWeek(td.get(j).text());
                   scrapper.setExcessPrice(td.get(j).text());

                   scrapperList.add(scrapper);
                   System.out.println(td.get(j).text());
               }
            System.out.println(header.get(i).text());
            System.out.println("*");
        }
    }
}
