package com.banger.backend.CSV_Reader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class Reader {

    @Scheduled(cron = "0 1 0 * * *", zone="Asia/Calcutta")
    public void readRequest() throws IOException {

        String char1 = "";
        String char2 = "";

        String url = "http://localhost:8081/csvReader/sendLicenseDetails";
        InputStream response = new URL(url).openStream();

        File file = new File("src/main/java/com/banger/backend/FileWriter/License.csv");
        FileWriter fileWriter = new FileWriter(file, true);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response));

        while ((char2=bufferedReader.readLine())!=null){
            char1=char2;
        }
        bufferedReader.close();
        char1=char1.replace("\"", "");
        char1=char1.replace("[","");
        char1=char1.replace("]","");

        PrintWriter printWriter = new PrintWriter(new File(String.valueOf(file)));

        List<String> list = new ArrayList<>(Arrays.asList(char1.split(",")));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<list.size(); i++){
            stringBuilder.append(list.get(i));
            stringBuilder.append('\n');
        }
        printWriter.write(stringBuilder.toString());
        printWriter.flush();
        printWriter.close();

    }
}