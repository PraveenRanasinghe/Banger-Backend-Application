package com.banger.backend.Scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class WebScrapingImplementation {

    public List<Scrapper> getScrapperList() throws IOException {
        Document doc = Jsoup.connect("https://www.malkey.lk/rates/self-drive-rates.html").get();

        Elements tr =doc.select("tr");
        Elements td =doc.select("td");

        List<Scrapper> scrapperList = new ArrayList<>();
        for(int i=0;i<tr.size();i++){

            Element element =(tr.get(i));

            Scrapper scrapper = new Scrapper();
            scrapper.setVehicleType(element.text());
            scrapperList.add(scrapper);
        }
        return scrapperList;
    }
}