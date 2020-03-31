package com.richa.coronavirus.services;

import com.richa.coronavirus.models.Location;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    private static String url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<Location> allStats = new ArrayList<>();

    public List<Location> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    //Execute this method every second using @Scheduled with cron second min hour
    public void fetchVirusData() throws IOException, InterruptedException {

         List<Location> newStats = new ArrayList<>();

        HttpClient httpClient = HttpClient.newHttpClient();  //HttpClient can be used to send requests and retrieve their responses.

        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        //System.out.println(response.body());

        StringReader csvReader = new StringReader(response.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
        for (CSVRecord record : records) {

            Location locationStat =new Location();

            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
            locationStat.setLatestTotalCases(Integer.valueOf(record.get(record.size()-1)));

            //System.out.println(locationStat);
            newStats.add(locationStat);
        }
       this.allStats = newStats;
    }
}
