package com.SuiviAlimentaire.HistoriqueNutritionnel.controller;



import com.SuiviAlimentaire.HistoriqueNutritionnel.entity.Repas;
import com.SuiviAlimentaire.HistoriqueNutritionnel.service.RepasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repas")
public class RepasController {

    @Autowired
    private RepasService repasService;

    @PostMapping
    public ResponseEntity<Repas> enregistrerRepas(@RequestBody Repas repas) {
        return ResponseEntity.ok(repasService.enregistrerRepas(repas));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Repas>> getHistorique(@PathVariable Long userId) {
        return ResponseEntity.ok(repasService.getHistoriqueByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repas> getRepas(@PathVariable Long id) {
        return repasService.getRepasById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repas> modifierRepas(@PathVariable Long id, @RequestBody Repas repas) {
        return ResponseEntity.ok(repasService.modifierRepas(id, repas));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerRepas(@PathVariable Long id) {
        repasService.supprimerRepas(id);
        return ResponseEntity.noContent().build();
    }
}