package com.SuiviAlimentaire.IACalories.controller;

import com.SuiviAlimentaire.IACalories.entities.CaloriesRecord;
import com.SuiviAlimentaire.IACalories.service.CaloriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/calories")
@RequiredArgsConstructor
public class CaloriesController {

    private final CaloriesService caloriesService;

    @GetMapping
    public List<CaloriesRecord> getAll() {
        return caloriesService.getAll();
    }
    @GetMapping("/")
    public List<CaloriesRecord> root() {
        return caloriesService.getAll();
    }
}
