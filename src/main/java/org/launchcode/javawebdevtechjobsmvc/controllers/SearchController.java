package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 * columnChoices.put("all", "All");
 *         columnChoices.put("employer", "Employer");
 *         columnChoices.put("location", "Location");
 *         columnChoices.put("positionType", "Position Type");
 *         columnChoices.put("coreCompetency", "Skill");
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        model.addAttribute("searchType","all");
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping
    public String displaySearchResults(@RequestParam String searchType, @RequestParam String searchTerm, Model model){
        ArrayList<Job> jobs;
        if (searchType == "all"){
            jobs = JobData.findByValue(searchTerm);
        }else {
            jobs = JobData.findByColumnAndValue(searchType,searchTerm);
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobs",jobs);
        model.addAttribute("searchType",searchType);
        model.addAttribute("searchTerm",searchTerm);
        return "search";
    }
}
