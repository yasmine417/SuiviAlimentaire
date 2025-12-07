package com.SuiviAlimentaire.OCRIngestion.controller;

import com.SuiviAlimentaire.OCRIngestion.dto.OcrResultDTO;
import com.SuiviAlimentaire.OCRIngestion.service.OcrService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ocr")
@RequiredArgsConstructor
public class OcrController {

    private final OcrService ocrService;

    @PostMapping("/send")
    public String sendOcr(@RequestBody OcrResultDTO dto) {
        ocrService.processOcr(dto.getMealName(), dto.getOcrText());
        return "OCR envoyé dans Kafka ✔️";
    }

}
