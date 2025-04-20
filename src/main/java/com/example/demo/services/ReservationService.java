package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.repositories.IChambreRepository;
import com.example.demo.repositories.IEtudiantRepository;
import com.example.demo.repositories.IReservationRepository;
//import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
//@AllArgsConstructor
public class ReservationService implements IReservationService{
    @Autowired
    IReservationRepository reservationRepository;

    @Autowired
    private IChambreRepository chambreRepository;

    @Autowired
    private IEtudiantRepository etudiantRepository;
    @Override
    public List<Reservation> retrieveAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation res) {
        return reservationRepository.save(res);
    }

    @Override
    public Reservation retrieveReservation(String idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }

    @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversite, String nomUniversite) {
        return reservationRepository.getReservationParAnneeUniversitaireEtNomUniversite(anneeUniversite, nomUniversite);
    }
    @Override
    public Reservation ajouterReservation(long idBloc, long cinEtudiant) {

        List<Chambre> chambres = chambreRepository.findByBlocIdBloc(idBloc);

        Chambre chambreDisponible = chambres.stream()
                .filter(chambre -> chambre.getReservations().size() < getCapacityForType(chambre.getTypeC()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Aucune chambre disponible dans le bloc : " + idBloc));

        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec le CIN : " + cinEtudiant));

        Reservation reservation = new Reservation();
        reservation.setIdReservation(chambreDisponible.getNumeroChambre() + "-" + new Date().getYear());
        reservation.setAnneeUniversitaire(new Date());
        reservation.setEstValide(true);

        reservation.getEtudiants().add(etudiant);
        chambreDisponible.getReservations().add(reservation);

        chambreRepository.save(chambreDisponible);

        return reservationRepository.save(reservation);
    }



    private int getCapacityForType(TypeChambre type) {
        switch (type) {
            case SIMPLE:
                return 1;
            case DOUBLE:
                return 2;
            case TRIPLE:
                return 3;
            default:
                throw new IllegalArgumentException("Type de chambre non valide");
        }
    }
    @Override
    public Reservation annulerReservation(long cinEtudiant) {
        // Étape 1 : Récupérer l'étudiant par son CIN
        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec le CIN : " + cinEtudiant));

        // Étape 2 : Récupérer la réservation active de l'étudiant
        Reservation reservation = reservationRepository.findByEtudiantsContainsAndEstValideIsTrue(etudiant)
                .orElseThrow(() -> new RuntimeException("Aucune réservation active trouvée pour l'étudiant avec le CIN : " + cinEtudiant));

        // Étape 3 : Mettre à jour l'état de la réservation
        reservation.setEstValide(false);

        // Étape 4 : Désaffecter l'étudiant de la réservation
        reservation.getEtudiants().remove(etudiant);

        // Étape 5 : Récupérer la chambre associée à la réservation (si possible)
        Optional<Chambre> chambreOpt = reservationRepository.findChambreByReservationId(reservation.getIdReservation());
        if (chambreOpt.isPresent()) {
            Chambre chambre = chambreOpt.get();
            chambre.getReservations().remove(reservation); // Désaffecter la réservation de la chambre
            chambreRepository.save(chambre); // Mettre à jour la chambre
        }

        // Étape 6 : Sauvegarder la réservation mise à jour
        return reservationRepository.save(reservation);
    }
}
