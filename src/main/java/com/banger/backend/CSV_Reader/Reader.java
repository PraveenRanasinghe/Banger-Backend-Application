package com.banger.backend.CSV_Reader;
import org.springframework.scheduling.annotation.Scheduled;


public class Reader {

    @Scheduled(cron = "0 0/01 12 * * *")
    public void readRequest(){

    }
}
