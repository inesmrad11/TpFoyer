package com.example.demo.controller;

import com.example.demo.entities.Reservation;
import com.example.demo.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservations")

public class ReservationController {
    @Autowired
    private IReservationService reservationService;

    @GetMapping("/get-all")
    public List<Reservation> getAllReservations() {
        return reservationService.retrieveAllReservation();
    }

    @GetMapping("/get-reservation/{id}")
    public Reservation getReservationById(@PathVariable String id) {
        return reservationService.retrieveReservation(id);
    }

    @PutMapping("/update-reservation")
    public Reservation updateReservation(Reservation res){
        return reservationService.updateReservation(res);
    }

    @GetMapping("/by-annee-universite")
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(@RequestParam Date anneeUniversite, @RequestParam String nomUniversite) {
        return reservationService.getReservationParAnneeUniversitaireEtNomUniversite(anneeUniversite, nomUniversite);
    }
    @PostMapping("/ajouter")
    public Reservation ajouterReservation(@RequestParam long idChambre, @RequestParam long cinEtudiant) {
        return reservationService.ajouterReservation(idChambre, cinEtudiant);
    }
}
