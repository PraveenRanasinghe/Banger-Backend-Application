package com.banger.backend.CSV_Reader;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
public class Reader {

    @PostConstruct
    public void readRequest() throws IOException {

        String url = "http://localhost:8081/csvReader/sendLicenseDetails";
        InputStream response = new URL(url).openStream();

        File file = new File("src/main/java/com/banger/backend/FileWriter/License.csv");
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);

        try (Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
            for (int i = 0; i < responseBody.length(); i++){
                printWriter.println(i);
            }
            printWriter.close();
        }
    }
}
