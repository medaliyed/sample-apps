package com.example.corona.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.corona.model.LocationStats;
import com.example.corona.service.CoronaDataService;

@Controller
public class HomeControler {

	@Autowired
	CoronaDataService coronaDataService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats =	coronaDataService.getAllStats();
		int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int newCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats",allStats);
		model.addAttribute("totalReportedCases",totalCases);
		model.addAttribute("newCases",newCases);
		return "home";
	}
}
