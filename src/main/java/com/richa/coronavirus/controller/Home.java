package com.richa.coronavirus.controller;

import com.richa.coronavirus.models.Location;
import com.richa.coronavirus.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Home {

    @Autowired
    public CoronaVirusDataService coronaVirusDataService;


    @GetMapping("/")
    public String home(Model model){
        List<Location> allStats = coronaVirusDataService.getAllStats();
        model.addAttribute("locationStats",allStats);

        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        model.addAttribute("totalReportedCases", totalReportedCases);

        return "home";
    }
}
