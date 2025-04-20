package com.example.demo.controller;

import com.example.demo.entities.Universite;
import com.example.demo.services.IUniversiteService;
//import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universites")
//@AllArgsConstructor
public class UniversiteController {
    @Autowired
    IUniversiteService universiteService;

    @GetMapping("/all-universities")
    public List<Universite> getAllUniversities() {
        return universiteService.retrieveAllUniversities();
    }

    @GetMapping("/get-universite/{id}")
    public Universite getUniversiteById(@PathVariable("id") long id) {
        return universiteService.retrieveUniversite(id);
    }

    @PostMapping("/add-universite")
    public ResponseEntity<Universite> addUniversite(@RequestBody Universite universite) {
        Universite savedUniversite = universiteService.addUniversite(universite);
        return ResponseEntity.ok(savedUniversite);
    }


    @PutMapping("/update-universite")
    public Universite updateUniversite(@RequestBody Universite universite) {
        return universiteService.updateUniversite(universite);
    }

    @PutMapping("/affecter-foyer/{idFoyer}/{nomUniversite}")
    public Universite affecterFoyerAUniversite(@PathVariable long idFoyer, @PathVariable String nomUniversite) {
        return universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }

    @PostMapping("/desaffecter/{idUniversite}")
    public Universite desaffecterFoyerAUniversite(@PathVariable long idUniversite) {
        return universiteService.desaffecterFoyerAUniversite(idUniversite);
    }
}