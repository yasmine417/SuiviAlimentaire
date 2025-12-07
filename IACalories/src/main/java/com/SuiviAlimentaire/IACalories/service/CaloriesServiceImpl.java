package com.SuiviAlimentaire.IACalories.service;

import com.SuiviAlimentaire.IACalories.dto.OcrResultDTO;
import com.SuiviAlimentaire.IACalories.entities.CaloriesRecord;
import com.SuiviAlimentaire.IACalories.repository.CaloriesRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CaloriesServiceImpl implements CaloriesService {

    private final CaloriesRecordRepository repository;

    @Override
    public CaloriesRecord processAndSave(OcrResultDTO dto) {

        double calories = estimateCalories(dto.getMealName());

        CaloriesRecord record = CaloriesRecord.builder()
                .mealName(dto.getMealName())
                .ocrText(dto.getOcrText())
                .calories(calories)
                .createdAt(LocalDateTime.now())
                .build();

        return repository.save(record);
    }

    private double estimateCalories(String meal) {

        meal = meal.toLowerCase();

        if (meal.contains("pizza")) return 300;
        if (meal.contains("salade")) return 120;
        if (meal.contains("poulet")) return 250;

        return 180; // valeur par défaut
    }

    @Override
    public List<CaloriesRecord> getAll() {
        return repository.findAll();
    }
}