package com.getyourguide.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class ActivityController {
    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/debug")
    public void debug(@RequestParam(name = "title", required = false, defaultValue = "NONE") String title, Model model) {
        try {
            model.addAttribute("title", title);
            //create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();
            //read JSON file and convert to a list of activities
            var file = resourceLoader.getResource("classpath:static/activities.json").getFile();
            var activities = objectMapper.readValue(file, new TypeReference<List<Activity>>() {
            });
            model.addAttribute("activities", activities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/activities")
    public ResponseEntity<List<Activity>> activities() {
        List<Activity> activities = null;
        try {
            //create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();
            //read JSON file and convert to a list of activities
            var file = resourceLoader.getResource("classpath:static/activities.json").getFile();
            activities = objectMapper.readValue(file, new TypeReference<List<Activity>>() {
            });

            return ResponseEntity.ok(activities);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
