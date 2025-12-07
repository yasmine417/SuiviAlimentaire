package com.SuiviAlimentaire.IACalories.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaloriesRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mealName;    // ex : "Pizza", "Salade"
    private Double calories;    // résultat IA

    @Column(columnDefinition = "TEXT")
    private String ocrText;     // texte extrait par OCR

    private LocalDateTime createdAt;
}
