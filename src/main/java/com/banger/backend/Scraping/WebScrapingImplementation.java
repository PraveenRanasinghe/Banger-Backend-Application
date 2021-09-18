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

        List<Scrapper> scrapperList = new ArrayList<>();

        for(Element rowElement: doc.select("table.table.selfdriverates tr")){

            Scrapper scrapper = new Scrapper();
            final String vehicleName = rowElement.select("td.text-left.percent-40").text();

            if(!vehicleName.contentEquals("")){
                scrapper.setVehicleType(vehicleName);
            }
            final String prices = rowElement.select("td.text-center.percent-22").text();
            if (!prices.contentEquals("")){
                String [] list = prices.split(" ");

                scrapper.setRatePerMonth(list[0]);
                scrapper.setRatePerWeek(list[1]);
                scrapper.setExcessPrice(list[2]);
                scrapperList.add(scrapper);
            }
        }
        return scrapperList;
    }
}