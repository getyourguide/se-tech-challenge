package com.getyourguide.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ActivityController {
    @Autowired
    private ResourceLoader resourceLoader;


    @GetMapping("/activities")
    public String activities(@RequestParam(name = "title", required = false, defaultValue = "NONE") String title, Model model) {
        model.addAttribute("title", title);

        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        //read JSON file and convert to a list of activities
        try {
            var file = resourceLoader.getResource("classpath:static/activities.json").getFile();
            var activities = objectMapper.readValue(file, new TypeReference<List<Activity>>(){});
            model.addAttribute("activities", activities);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "activities";
    }
}
