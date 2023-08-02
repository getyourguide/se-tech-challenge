package com.getyourguide.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.demo.model.Activity;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ActivitiesService {

    @Autowired
    private ResourceLoader resourceLoader;

    private List<Activity> activities;

    @PostConstruct
    private void loadActivities() {
        try {
            //create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();
            //read JSON file and convert to a list of activities
            var file = resourceLoader.getResource("classpath:static/activities.json").getFile();
            activities = objectMapper.readValue(file, new TypeReference<List<Activity>>() {
            });
        } catch (IOException e) {
            activities = List.of();
            log.error("Could not read activities json, check what's wrong {}", e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Activity> getActivitiesByTitle(String title){
        return activities.stream()
                .filter(activity -> StringUtils.startsWithIgnoreCase(activity.getTitle(), title))
                .collect(Collectors.toList());
    }

}
