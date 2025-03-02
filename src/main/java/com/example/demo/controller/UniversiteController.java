package com.example.demo.controller;

import com.example.demo.entities.Universite;
import com.example.demo.services.IUniversiteService;
//import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Universite addUniversite(@RequestBody Universite universite) {
        return universiteService.addUniversite(universite);
    }

    @PutMapping("/update-universite")
    public Universite updateUniversite(@RequestBody Universite universite) {
        return universiteService.updateUniversite(universite);
    }
}