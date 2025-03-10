package com.example.demo.controller;

import com.example.demo.entities.Chambre;
import com.example.demo.entities.TypeChambre;
import com.example.demo.services.IChambreService;
//import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chambre")
public class ChambreController {
    @Autowired
    IChambreService chambreService;

    @GetMapping("/all")
    public List<Chambre> getAll(){
        return chambreService.retrieveAllChambres();
    }

    @PostMapping("/add")
    public Chambre addChambres(@RequestBody Chambre chambre){
        return chambreService.addChambre(chambre);
    }

    @PutMapping("/update")
    public Chambre updateChambre(@RequestBody Chambre chambre){
        return chambreService.updateChambre(chambre);
    }

    @GetMapping("/get/{id}")
    public Chambre getChambre(@PathVariable("id") Long id){
        return chambreService.retrieveChambre(id);
    }

    // Get non-reserved chambres by university name and room type
    @GetMapping("/non-reservees")
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(@RequestParam String nomUniversite, @RequestParam TypeChambre type) {
        return chambreService.getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite, type);
    }

    // Endpoint using JPQL
    @GetMapping("/by-bloc-and-type-jpql")
    public List<Chambre> getChambresParBlocEtTypeJPQL(@RequestParam long idBloc, @RequestParam TypeChambre typeC) {
        return chambreService.getChambresParBlocEtType(idBloc, typeC);
    }

    // Endpoint using Spring Data JPA Keywords
    @GetMapping("/by-bloc-and-type-keywords")
    public List<Chambre> getChambresParBlocEtTypeKeywords(@RequestParam long idBloc, @RequestParam TypeChambre typeC) {
        return chambreService.findByBlocIdBlocAndTypeC(idBloc, typeC);
    }
}