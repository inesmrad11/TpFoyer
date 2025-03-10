package com.example.demo.repositories;

import com.example.demo.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation,String> {
    @Query(value = "SELECT r.* FROM reservation r " +
            "JOIN reservation_etudiant re ON r.id_reservation = re.reservation_id " +
            "JOIN etudiant e ON re.etudiant_id = e.id_etudiant " +
            "JOIN universite u ON e.universite_id = u.id_universite " +
            "WHERE u.nom_universite = :nomUniversite " +
            "AND r.annee_universitaire = :anneeUniversite", nativeQuery = true)
    List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(@Param("anneeUniversite") Date anneeUniversite, @Param("nomUniversite") String nomUniversite);
}
