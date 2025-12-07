package com.SuiviAlimentaire.HistoriqueNutritionnel.kafka;

import com.SuiviAlimentaire.HistoriqueNutritionnel.entity.Repas;
import com.SuiviAlimentaire.HistoriqueNutritionnel.service.RepasService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RepasKafkaListener {

    private static final Logger logger = LoggerFactory.getLogger(RepasKafkaListener.class);

    @Autowired
    private RepasService repasService;

    @Autowired
    private ObjectMapper objectMapper;  // Pour parser JSON, ajoutez @Bean si nécessaire

    @KafkaListener(topics = "${kafka.topic.repas-detectes}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenRepasDetectes(String message) {
        try {
            // Assumez que le message est un JSON de Repas (envoyé par le service OCR/IA)
            Repas repas = objectMapper.readValue(message, Repas.class);
            repasService.enregistrerRepas(repas);
            logger.info("Repas enregistré depuis Kafka: {}", repas.getId());
        } catch (Exception e) {
            logger.error("Erreur lors du traitement du message Kafka: {}", e.getMessage());
        }
    }
}